package pers.jiangyinzuo.carbon.domain.dto;

import lombok.Data;

/**
 * @author Jiang Yinzuo
 */
@Data
public class LeaderBoardUserInfoDTO {
    private Long userId;
    private String userName;
    private Double credit;
    private Integer badgeCount;
}
