package pers.jiangyinzuo.carbon.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.jiangyinzuo.carbon.http.AjaxResponse;
import pers.jiangyinzuo.carbon.domain.dto.UserInfoDTO;

/**
 * @author Jiang Yinzuo
 */
@RestController
public class UserController {

    @GetMapping("/user/userInfo")
    public AjaxResponse getUserInfo() {
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setToken("24");
        return AjaxResponse.success(userInfoDTO);
    }
}
