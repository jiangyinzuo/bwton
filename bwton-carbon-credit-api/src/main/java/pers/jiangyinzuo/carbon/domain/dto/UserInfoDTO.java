package pers.jiangyinzuo.carbon.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import pers.jiangyinzuo.carbon.domain.entity.User;

/**
 * @author Jiang Yinzuo
 */
@Data
@AllArgsConstructor
@ApiModel("用户信息")
public class UserInfoDTO {

    @ApiModelProperty("用户")
    private User user;

    @ApiModelProperty("token")
    private String token;
}
