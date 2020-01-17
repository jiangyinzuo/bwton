package pers.jiangyinzuo.carbon.domain.entity;

import io.swagger.annotations.Api;
import lombok.Builder;
import lombok.Data;

/**
 * @author Jiang Yinzuo
 */
@Data
@Builder
@Api("用户")
public class User {
    private Long userId;
    private String nickname;
    private String password;
    private String telephone;
    private java.sql.Timestamp createTime;
}
