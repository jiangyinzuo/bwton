package pers.jiangyinzuo.carbon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pers.jiangyinzuo.carbon.common.security.EncryptUtil;
import pers.jiangyinzuo.carbon.common.security.SaltGenerator;
import pers.jiangyinzuo.carbon.dao.mapper.UserMapper;
import pers.jiangyinzuo.carbon.domain.bo.UserLoginBO;
import pers.jiangyinzuo.carbon.domain.dto.UserLoginDTO;
import pers.jiangyinzuo.carbon.domain.dto.UserRegisterDTO;
import pers.jiangyinzuo.carbon.http.CustomRequestException;
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
        UserLoginBO userLoginBO = userMapper.getUserAccountByTelephone(userLoginDTO.telephone());
        if (userLoginBO == null) {
            return ACCOUNT_NOT_EXIST;
        }
        if (EncryptUtil.encryptPassword(userLoginDTO.password(), userLoginBO.salt()).equals(userLoginBO.cipher())) {
            return userLoginBO.userId();
        } else {
            return PASSWORD_ERROR;
        }
    }
    
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(UserRegisterDTO userRegisterDTO) throws CustomRequestException {
        byte[] salt = SaltGenerator.getSalt32();
        String cipher = EncryptUtil.encryptPassword(userRegisterDTO.password(), salt);
        try {
            userMapper.saveUserAccount(
                    userRegisterDTO.nickname(),
                    cipher,
                    salt,
                    userRegisterDTO.telephone()
            );
        } catch (DuplicateKeyException e) {
            throw new CustomRequestException(HttpStatus.OK, "手机号已被注册", 1);
        }
    }
}
