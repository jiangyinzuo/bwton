package pers.jiangyinzuo.carbon.domain.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import pers.jiangyinzuo.carbon.domain.validation.annotation.Password;
import pers.jiangyinzuo.carbon.domain.validation.annotation.Telephone;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@JsonAutoDetect
public record UserLoginDTO(
        @NotEmpty(message = "参数`telephone`未传")
        @NotBlank(message = "手机号不能为空")
        @Telephone
        String telephone,

        @NotEmpty(message = "参数`password`未传")
        @NotBlank(message = "密码不能为空")
        @Password
        String password
) {}
