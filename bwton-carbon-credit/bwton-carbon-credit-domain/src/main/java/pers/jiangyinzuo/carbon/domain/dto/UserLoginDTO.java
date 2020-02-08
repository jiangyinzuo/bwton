package pers.jiangyinzuo.carbon.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import pers.jiangyinzuo.carbon.domain.validation.annotation.Password;
import pers.jiangyinzuo.carbon.domain.validation.annotation.Telephone;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * @author Jiang Yinzuo
 */
@Data
public class UserLoginDTO {

    @JsonIgnore
    private Long userId;

    @NotEmpty(message = "参数`telephone`未传")
    @NotBlank(message = "手机号不能为空")
    @Telephone
    private String telephone;

    @NotEmpty(message = "参数`password`未传")
    @NotBlank(message = "密码不能为空")
    @Password
    private String password;

    @JsonIgnore
    private byte[] salt;

    @JsonIgnore
    private String cipher;
}
