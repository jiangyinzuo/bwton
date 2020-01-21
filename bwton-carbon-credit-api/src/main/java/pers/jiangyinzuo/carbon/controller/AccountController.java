package pers.jiangyinzuo.carbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pers.jiangyinzuo.carbon.common.security.Sha256Util;
import pers.jiangyinzuo.carbon.domain.dto.UserLoginDTO;
import pers.jiangyinzuo.carbon.domain.dto.UserRegisterDTO;
import pers.jiangyinzuo.carbon.http.HttpResponseBody;
import pers.jiangyinzuo.carbon.service.AccountService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jiang Yinzuo
 */
@RestController
public class AccountController {

    private AccountService accountService;

    @Autowired
    public void setAccountService(AccountService accountService) {
        this.accountService = accountService;
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
            data.put("token", Sha256Util.genToken(userLoginDTO.getPassword()));
            return new HttpResponseBody<>(0, "ok", data);
        } else {
            return new HttpResponseBody<>(1, status.getDesc(), null);
        }
    }

}
