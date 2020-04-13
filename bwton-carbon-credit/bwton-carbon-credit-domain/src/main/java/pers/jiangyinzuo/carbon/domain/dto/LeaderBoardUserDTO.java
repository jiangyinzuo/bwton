package pers.jiangyinzuo.carbon.domain.dto;

import pers.jiangyinzuo.carbon.domain.entity.User;

/**
 * @author Jiang Yinzuo
 */
public record LeaderBoardUserDTO(
        Long userId,
        String nickname,
        Long credit,
        Integer badgeCount) implements Comparable<LeaderBoardUserDTO> {

    public static LeaderBoardUserDTO getInstance(User user, Long credit) {
        int badgeCount = 0;
        long badge = user.badge();
        while (badge != 0L) {
            badgeCount += (badge & 1) == 1 ? 1 : 0;
            badge >>= 1;
        }
        return new LeaderBoardUserDTO(user.userId(), user.nickname(), credit, badgeCount);
    }

    @Override
    public int compareTo(LeaderBoardUserDTO o) {
        return o.credit.compareTo(credit);
    }
}
