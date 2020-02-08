package pers.jiangyinzuo.carbon.service;

import pers.jiangyinzuo.carbon.http.BusinessException;
import pers.jiangyinzuo.carbon.domain.dto.UserLoginDTO;
import pers.jiangyinzuo.carbon.domain.dto.UserRegisterDTO;

/**
 * @author Jiang Yinzuo
 */
public interface AccountService {

    long PASSWORD_ERROR = -1L;
    long ACCOUNT_NOT_EXIST = -2L;

    /**
     * 登录
     * @param userLoginDTO 用户登录DTO
     * @return 登录结果: 成功返回userId，密码错误返回-1，用户不存在返回-2
     */
    Long login(UserLoginDTO userLoginDTO);

    /**
     * 注册账号，一个手机号只能注册一个用户账号
     * @param userRegisterDTO 用户注册DTO
     */
    void register(UserRegisterDTO userRegisterDTO) throws BusinessException;
}
