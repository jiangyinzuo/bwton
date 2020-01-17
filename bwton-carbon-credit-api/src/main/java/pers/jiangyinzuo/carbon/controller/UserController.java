package pers.jiangyinzuo.carbon.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pers.jiangyinzuo.carbon.domain.dto.UserInfoDTO;
import pers.jiangyinzuo.carbon.domain.entity.User;
import pers.jiangyinzuo.carbon.http.HttpResponse;
import pers.jiangyinzuo.carbon.mapper.UserMapper;


/**
 * @author Jiang Yinzuo
 */
@RestController
@Api(value = "用户", tags = {"用户"})
public class UserController {

    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @GetMapping("/user/userInfo")
    @ApiOperation("获取用户信息")
    public HttpResponse<UserInfoDTO> getUserInfo() {
        return new HttpResponse<>(0, null, new UserInfoDTO(null, "35"));
    }

    @GetMapping("/user/{user_id}")
    public HttpResponse<User> getUser(@PathVariable("user_id") Long userId) {
        return new HttpResponse<>(0, null, userMapper.getUserByUserId(userId));
    }

    @GetMapping("/user")
    public HttpResponse<Object> insertUser(User user) {
        userMapper.insertUser(user);
        return new HttpResponse<>(0, null, null);
    }
}
