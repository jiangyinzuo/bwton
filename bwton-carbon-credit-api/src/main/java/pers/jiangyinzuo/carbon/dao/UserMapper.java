package pers.jiangyinzuo.carbon.dao;

import pers.jiangyinzuo.carbon.domain.dto.UserAccountDTO;

/**
 * @author Jiang Yinzuo
 */
public interface UserMapper {

    /**
     * 根据用户手机号获取用户账号信息，用于登录
     *
     * @param telephone 手机号
     * @return 用户账号DTO
     */
    UserAccountDTO getUserAccountByTelephone(String telephone);

    /**
     * 添加用户账号
     *
     * @param nickname  用户昵称
     * @param password  用户密码
     * @param salt      盐值，用于加密
     * @param telephone 手机号
     */
    void saveUserAccount(String nickname, String password, byte[] salt, String telephone);
}
