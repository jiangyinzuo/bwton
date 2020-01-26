package pers.jiangyinzuo.carbon.domain.entity;

import lombok.Builder;
import lombok.Data;

/**
 * @author Jiang Yinzuo
 */
@Data
@Builder
public class User {
    private Long userId;
    private String nickname;
    private String password;
    private String telephone;
    private java.sql.Timestamp createTime;
}
