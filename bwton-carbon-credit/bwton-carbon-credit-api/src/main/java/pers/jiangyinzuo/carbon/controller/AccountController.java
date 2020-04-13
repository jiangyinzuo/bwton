package pers.jiangyinzuo.carbon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pers.jiangyinzuo.carbon.domain.dto.UserLoginDTO;
import pers.jiangyinzuo.carbon.domain.dto.UserRegisterDTO;
import pers.jiangyinzuo.carbon.domain.vo.LoginVO;
import pers.jiangyinzuo.carbon.http.CustomRequestException;
import pers.jiangyinzuo.carbon.http.HttpResponseUtil;
import pers.jiangyinzuo.carbon.service.AccountService;
import pers.jiangyinzuo.carbon.service.TokenService;
import pers.jiangyinzuo.carbon.util.HttpHeaderUtil;

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

    /**
     * 用户注册
     * @param userRegisterDTO 请求体
     * @return Http请求
     * @throws CustomRequestException 业务逻辑错误
     */
    @PostMapping("/register")
    public ResponseEntity<Object> register(
            @Validated @RequestBody UserRegisterDTO userRegisterDTO
            ) throws CustomRequestException {
        accountService.register(userRegisterDTO);
        return HttpResponseUtil.OK;
    }

    /**
     * 用户登录
     * @param userLoginDTO
     * @return
     */
    @PostMapping(value = "/login")
    public ResponseEntity<Object> login(
            @Validated @RequestBody UserLoginDTO userLoginDTO
            ) {
        Long userId = accountService.login(userLoginDTO);
        if (userId > 0) {
            String token = tokenService.genBase64Token(userId.toString());
            return HttpResponseUtil.ok(new LoginVO(userId, token));
        } else {
            String errMsg = userId == AccountService.PASSWORD_ERROR ? "密码错误" : "账号不存在";
            return HttpResponseUtil.ok(1, errMsg);
        }
    }

    /**
     * 刷新token
     * @param authToken
     * @return
     */
    @PutMapping("/token")
    public ResponseEntity<Object> refreshToken(
            @RequestHeader("Authorization") String authToken) {
        String newBase64Token = tokenService.refreshBase64Token(authToken.substring(7));
        Long userId = HttpHeaderUtil.getUserId(authToken);
        Map<String, Object> data = new HashMap<>(1);
        data.put("token", newBase64Token);
        data.put("userId", userId);
        return HttpResponseUtil.ok(data);
    }
}
