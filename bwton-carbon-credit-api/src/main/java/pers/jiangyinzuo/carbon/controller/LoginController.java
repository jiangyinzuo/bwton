package pers.jiangyinzuo.carbon.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.jiangyinzuo.carbon.http.HttpResponseBody;

/**
 * @author Jiang Yinzuo
 */
@RestController
public class LoginController {

    @PostMapping("/register")
    public HttpResponseBody<Object> register() {
        return null;
    }
}
