package pers.jiangyinzuo.carbon.domain.dto;

import lombok.Data;
import pers.jiangyinzuo.carbon.domain.entity.User;

import java.util.Map;

/**
 * @author Jiang Yinzuo
 */
@Data
public class LeaderBoardUserDTO implements Comparable<LeaderBoardUserDTO> {
    private Long userId;
    private String nickname;
    private Long credit;
    private Integer badgeCount;

    public LeaderBoardUserDTO(Map<String, String> user, Long credit) {
        this.userId = Long.parseLong(user.get("userId"));
        this.nickname = user.get("nickname");
        this.credit = credit;
        this.badgeCount = 0;
        long badge = Long.parseLong(user.get("badge"));
        while (badge != 0L) {
            badgeCount += (badge & 1) == 1 ? 1 : 0;
            badge >>= 1;
        }
    }

    @Override
    public int compareTo(LeaderBoardUserDTO o) {
        return o.credit.compareTo(credit);
    }
}
