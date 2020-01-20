package pers.jiangyinzuo.carbon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import pers.jiangyinzuo.carbon.common.security.SaltGenerator;
import pers.jiangyinzuo.carbon.dao.UserMapper;
import pers.jiangyinzuo.carbon.domain.dto.UserAccountDTO;
import pers.jiangyinzuo.carbon.http.CustomHttpException;
import pers.jiangyinzuo.carbon.service.AccountService;

import static pers.jiangyinzuo.carbon.service.AccountService.LOGIN_STATUS.*;

/**
 * @author Jiang Yinzuo
 */
public class AccountServiceImpl implements AccountService {

    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public LOGIN_STATUS login(String telephone, String password) {
        UserAccountDTO userAccountDTO = userMapper.getUserAccountByTelephone(telephone);
        if (userAccountDTO == null) {
            return ACCOUNT_NOT_FOUND;
        }
        return SUCCESS;
    }

    @Override
    public void register(String nickname, String telephone, String password) {
        byte[] salt = SaltGenerator.getSalt32();
        try {
            userMapper.saveUserAccount(nickname, telephone, salt, password);
        } catch (DuplicateKeyException e) {
            throw new CustomHttpException(HttpStatus.ACCEPTED, "手机号已被注册", 1);
        }
    }
}
