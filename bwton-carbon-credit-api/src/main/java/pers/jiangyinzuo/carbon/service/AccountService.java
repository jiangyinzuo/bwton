package pers.jiangyinzuo.carbon.service;

import org.springframework.stereotype.Service;

/**
 * @author Jiang Yinzuo
 */
@Service
public interface AccountService {

    enum LOGIN_STATUS {
        /**
         * 登录结果
         */
        SUCCESS("登录成功"), ACCOUNT_NOT_FOUND("账号不存在"),
        PASSWORD_NOT_MATCHED("密码错误");

        private String desc;
        LOGIN_STATUS(String desc) {
            this.desc = desc;
        }
    }

    /**
     * 登录
     * @param telephone 用户手机号
     * @param password 用户密码
     * @return 登录结果
     */
    LOGIN_STATUS login(String telephone, String password);

    /**
     * 注册账号，一个手机号只能注册一个用户账号
     * @param nickname 用户昵称
     * @param telephone 用户手机号
     * @param password 登录密码
     */
    void register(String nickname, String telephone, String password);
}
