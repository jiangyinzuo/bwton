package pers.jiangyinzuo.carbon.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.jiangyinzuo.carbon.http.HttpResponse;
import pers.jiangyinzuo.carbon.domain.dto.UserInfoDTO;

/**
 * @author Jiang Yinzuo
 */
@RestController
public class UserController {

    @GetMapping("/user/userInfo")
    public HttpResponse getUserInfo() {
        return HttpResponse.success(new UserInfoDTO(null, "24"));
    }
}
