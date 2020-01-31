package pers.jiangyinzuo.carbon.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import pers.jiangyinzuo.carbon.domain.dto.UserLoginDTO;

import java.util.List;

/**
 * @author Jiang Yinzuo
 */
@Mapper
public interface UserMapper {

    /**
     * 根据用户手机号获取用户账号信息，用于登录
     *
     * @param telephone 手机号
     * @return 用户账号DTO
     */
    UserLoginDTO getUserAccountByTelephone(String telephone);

    /**
     * 添加用户账号
     *
     * @param nickname  用户昵称
     * @param cipher  用户密码
     * @param salt      盐值，用于加密
     * @param telephone 手机号
     */
    void saveUserAccount(String nickname, String cipher, byte[] salt, String telephone);

    List<Object> getUsers(List<Long> userIds);
}
