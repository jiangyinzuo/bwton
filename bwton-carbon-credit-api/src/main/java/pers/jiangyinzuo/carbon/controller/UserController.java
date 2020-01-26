package pers.jiangyinzuo.carbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pers.jiangyinzuo.carbon.dao.mapper.UserMapper;
import pers.jiangyinzuo.carbon.domain.entity.User;
import pers.jiangyinzuo.carbon.http.HttpResponseBody;


/**
 * @author Jiang Yinzuo
 */
@RestController
public class UserController {

    private UserMapper userMapper;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @GetMapping("/user/{user_id}")
    public HttpResponseBody<User> getUser(@PathVariable("user_id") Long userId) {
        return new HttpResponseBody<>(0, null, null);
    }

    @GetMapping("/user")
    public HttpResponseBody<Object> insertUser(User user) {
        return new HttpResponseBody<>(0, null, null);
    }
}
