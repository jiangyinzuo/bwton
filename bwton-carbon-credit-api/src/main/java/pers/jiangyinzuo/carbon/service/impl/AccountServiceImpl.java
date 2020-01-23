package pers.jiangyinzuo.carbon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pers.jiangyinzuo.carbon.common.security.SaltGenerator;
import pers.jiangyinzuo.carbon.common.security.Sha256Util;
import pers.jiangyinzuo.carbon.dao.mapper.UserMapper;
import pers.jiangyinzuo.carbon.domain.dto.UserLoginDTO;
import pers.jiangyinzuo.carbon.domain.dto.UserRegisterDTO;
import pers.jiangyinzuo.carbon.http.CustomHttpException;
import pers.jiangyinzuo.carbon.service.AccountService;


/**
 * @author Jiang Yinzuo
 */
@Service
public class AccountServiceImpl implements AccountService {

    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public Long login(UserLoginDTO userLoginDTO) {
        UserLoginDTO dto = userMapper.getUserAccountByTelephone(userLoginDTO.getTelephone());
        if (dto == null) {
            return ACCOUNT_NOT_EXIST;
        }
        if (Sha256Util.encryptPassword(userLoginDTO.getPassword(), dto.getSalt()).equals(dto.getCipher())) {
            return dto.getUserId();
        } else {
            return PASSWORD_ERROR;
        }
    }

    @Override
    public void register(UserRegisterDTO userRegisterDTO) {
        byte[] salt = SaltGenerator.getSalt32();
        String cipher = Sha256Util.encryptPassword(userRegisterDTO.getPassword(), salt);
        try {
            userMapper.saveUserAccount(
                    userRegisterDTO.getNickname(),
                    cipher,
                    salt,
                    userRegisterDTO.getTelephone()
            );
        } catch (DuplicateKeyException e) {
            throw new CustomHttpException(HttpStatus.ACCEPTED, "手机号已被注册", 1);
        }
    }
}
