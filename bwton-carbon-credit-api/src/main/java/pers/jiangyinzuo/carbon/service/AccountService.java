package pers.jiangyinzuo.carbon.service;

import pers.jiangyinzuo.carbon.domain.dto.UserLoginDTO;
import pers.jiangyinzuo.carbon.domain.dto.UserRegisterDTO;

/**
 * @author Jiang Yinzuo
 */
public interface AccountService {

    enum LOGIN_STATUS {
        /**
         * 登录结果
         */
        SUCCESS("登录成功"), ACCOUNT_NOT_FOUND("账号不存在"),
        PASSWORD_NOT_MATCHED("密码错误");

        private String desc;

        public String getDesc() {
            return desc;
        }

        LOGIN_STATUS(String desc) {
            this.desc = desc;
        }
    }

    /**
     * 登录
     * @param userLoginDTO 用户登录DTO
     * @return 登录结果
     */
    LOGIN_STATUS login(UserLoginDTO userLoginDTO);

    /**
     * 注册账号，一个手机号只能注册一个用户账号
     * @param userRegisterDTO 用户注册DTO
     */
    void register(UserRegisterDTO userRegisterDTO);
}
