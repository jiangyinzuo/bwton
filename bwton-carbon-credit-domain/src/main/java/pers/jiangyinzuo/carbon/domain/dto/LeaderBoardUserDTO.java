package pers.jiangyinzuo.carbon.domain.dto;

import lombok.Data;
import pers.jiangyinzuo.carbon.domain.entity.User;

/**
 * @author Jiang Yinzuo
 */
@Data
public class LeaderBoardUserDTO {
    private Long userId;
    private String nickname;
    private Long credit;
    private Integer badgeCount;

    public LeaderBoardUserDTO(User user, Long credit) {
        this.userId = user.getUserId();
        this.nickname = user.getNickname();
        this.credit = credit;
        this.badgeCount = 0;
        long badge = user.getBadge();
        while (badge != 0L) {
            badgeCount += (badge & 1) == 1 ? 1 : 0;
            badge >>= 1;
        }
    }
}
