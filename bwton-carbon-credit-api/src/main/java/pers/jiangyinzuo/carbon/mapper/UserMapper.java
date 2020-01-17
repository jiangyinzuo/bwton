package pers.jiangyinzuo.carbon.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import pers.jiangyinzuo.carbon.domain.entity.User;

/**
 * @author Jiang Yinzuo
 */
@Mapper
public interface UserMapper {

    /**
     * query user by user_id
     * @param userId 用户ID
     * @return User实体类
     */
    @Select("SELECT * FROM bwton_user WHERE user_id=#{userId}")
    User getUserByUserId(Long userId);

    /**
     * 添加User
     * @param user user实体类
     */
    @Insert("INSERT INTO bwton_user(nickname, password, telephone) " +
            "VALUES(#{nickname}, #{password}, #{telephone}) ")
    void insertUser(User user);
}
