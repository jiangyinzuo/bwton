package pers.jiangyinzuo.carbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pers.jiangyinzuo.carbon.domain.dto.UserLoginDTO;
import pers.jiangyinzuo.carbon.domain.dto.UserRegisterDTO;
import pers.jiangyinzuo.carbon.common.http.HttpResponseBody;
import pers.jiangyinzuo.carbon.service.AccountService;
import pers.jiangyinzuo.carbon.service.TokenService;
import pers.jiangyinzuo.carbon.util.HttpHeaderUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Jiang Yinzuo
 */
@RestController
public class AccountController {

    private AccountService accountService;
    private TokenService tokenService;

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Autowired
    public void setTokenService(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping("/register")
    public HttpResponseBody<Object> register(
            @Validated @RequestBody UserRegisterDTO userRegisterDTO
            ) {
        accountService.register(userRegisterDTO);
        return new HttpResponseBody<>(0, "ok", null);
    }

    @PostMapping("/login")
    public HttpResponseBody<Map<String, String>> login(
            @Validated @RequestBody UserLoginDTO userLoginDTO
            ) {
        Long userId = accountService.login(userLoginDTO);
        if (userId > 0) {
            Map<String, String> data = new HashMap<>(1);
            data.put("token", tokenService.genBase64Token(userId.toString()));
            return new HttpResponseBody<>(0, "ok", data);
        } else {
            String desc = userId == AccountService.PASSWORD_ERROR ? "密码错误" : "账号不存在";
            return new HttpResponseBody<>(1, desc, null);
        }
    }

    @PutMapping("/token")
    public HttpResponseBody<Map<String, String>> refreshToken(HttpServletRequest request) {
        String base64Token = HttpHeaderUtil.getAuthBase64Token(request);
        String newBase64Token = tokenService.refreshBase64Token(base64Token);
        Map<String, String> data = new HashMap<>(1);
        data.put("token", newBase64Token);
        return new HttpResponseBody<>(0, "ok", data);
    }

}
