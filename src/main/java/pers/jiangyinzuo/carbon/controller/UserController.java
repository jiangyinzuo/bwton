package pers.jiangyinzuo.carbon.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Jiang Yinzuo
 */
@RestController
public class UserController {

    @GetMapping("/user/userInfo")
    public String getUserInfo() {
        return "user22";
    }
}
