package pers.jiangyinzuo.carbon.domain.dto;

import lombok.Data;
import pers.jiangyinzuo.carbon.domain.entity.User;

/**
 * @author Jiang Yinzuo
 */
@Data
public class UserInfoDTO {
    private User user;
    private String token;
}
