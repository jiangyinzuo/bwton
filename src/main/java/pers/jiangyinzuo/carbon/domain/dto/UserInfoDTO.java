package pers.jiangyinzuo.carbon.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import pers.jiangyinzuo.carbon.domain.entity.User;

/**
 * @author Jiang Yinzuo
 */
@Data
@AllArgsConstructor
public class UserInfoDTO {
    private User user;
    private String token;
}
