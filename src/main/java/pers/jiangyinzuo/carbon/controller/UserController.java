package pers.jiangyinzuo.carbon.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.jiangyinzuo.carbon.domain.dto.UserInfoDTO;
import pers.jiangyinzuo.carbon.http.HttpResponse;


/**
 * @author Jiang Yinzuo
 */
@RestController
@Api(value = "用户", tags = {"用户"})
public class UserController {

    @GetMapping("/user/userInfo")
    @ApiOperation("获取用户信息")
    public HttpResponse<UserInfoDTO> getUserInfo() {
        return new HttpResponse<>(0, null, new UserInfoDTO(null, "33"));
    }
}
