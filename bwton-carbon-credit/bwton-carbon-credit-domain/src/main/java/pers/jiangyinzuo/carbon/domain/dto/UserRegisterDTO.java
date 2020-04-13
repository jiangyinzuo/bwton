package pers.jiangyinzuo.carbon.domain.dto;

import pers.jiangyinzuo.carbon.domain.validation.annotation.Nickname;
import pers.jiangyinzuo.carbon.domain.validation.annotation.Password;
import pers.jiangyinzuo.carbon.domain.validation.annotation.Telephone;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * @author Jiang Yinzuo
 */
public record UserRegisterDTO(
        @NotBlank(message = "昵称不能为空")
        @NotEmpty(message = "参数`nickname`未传")
        @Nickname
        String nickname,

        @NotBlank(message = "手机号不能为空")
        @NotEmpty(message = "参数`telephone`未传")
        @Telephone
        String telephone,

        @NotBlank(message = "密码不能为空")
        @NotEmpty(message = "参数`password`未传")
        @Password
        String password
) {}
