package pers.jiangyinzuo.carbon.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pers.jiangyinzuo.carbon.domain.dto.UserRegisterDTO;
import pers.jiangyinzuo.carbon.http.HttpResponseBody;

/**
 * @author Jiang Yinzuo
 */
@RestController
public class AccountController {

    @PostMapping("/register")
    public HttpResponseBody<Object> register(
            @Validated @RequestBody UserRegisterDTO userRegisterDTO
            ) {
        return new HttpResponseBody<>(0, "ok", null);
    }
}
