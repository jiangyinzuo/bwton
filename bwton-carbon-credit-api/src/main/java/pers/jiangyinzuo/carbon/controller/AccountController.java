package pers.jiangyinzuo.carbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pers.jiangyinzuo.carbon.common.security.Sha256Util;
import pers.jiangyinzuo.carbon.domain.dto.UserLoginDTO;
import pers.jiangyinzuo.carbon.domain.dto.UserRegisterDTO;
import pers.jiangyinzuo.carbon.http.HttpResponseBody;
import pers.jiangyinzuo.carbon.service.AccountService;
import pers.jiangyinzuo.carbon.service.TokenService;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
        AccountService.LOGIN_STATUS status = accountService.login(userLoginDTO);
        if (status.equals(AccountService.LOGIN_STATUS.SUCCESS)) {
            Map<String, String> data = new HashMap<>(1);
            data.put("token", Sha256Util.genToken());
            return new HttpResponseBody<>(0, "ok", data);
        } else {
            return new HttpResponseBody<>(1, status.getDesc(), null);
        }
    }

    @PutMapping("/token")
    public HttpResponseBody<Map<String, String>> refreshToken(
            @Validated @Max(Long.MAX_VALUE) @Min(1) @RequestParam Long userId
    ) {
        String newToken = tokenService.refreshToken(userId);
        Map<String, String> data = new HashMap<>(1);
        data.put("token", newToken);
        return new HttpResponseBody<>(0, "ok", data);
    }

}
