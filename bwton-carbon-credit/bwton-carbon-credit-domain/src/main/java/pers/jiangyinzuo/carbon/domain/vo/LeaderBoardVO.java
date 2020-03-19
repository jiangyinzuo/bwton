package pers.jiangyinzuo.carbon.domain.vo;

import lombok.Data;
import pers.jiangyinzuo.carbon.domain.dto.LeaderBoardUserDTO;
import pers.jiangyinzuo.carbon.domain.entity.User;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author Jiang Yinzuo
 */
@Data
public class LeaderBoardVO {
    private LeaderBoardUserDTO user;

    /**
     * 排行榜中的用户必须按照积分排序
     */
    private Set<LeaderBoardUserDTO> leaderboard;

    private LeaderBoardVO() {
        this.leaderboard = new TreeSet<>();
    }

    public static LeaderBoardVO newLeaderBoardVO(List<User> userList, List<Long> creditList) {
        LeaderBoardVO vo = new LeaderBoardVO();

        // 该用户不存在
        if (userList.isEmpty()) {
            return vo;
        }
        vo.setUser(new LeaderBoardUserDTO((userList.get(0)), creditList.get(0)));
        int i = 0;
        for (User user : userList) {
            vo.leaderboard.add(new LeaderBoardUserDTO(user, creditList.get(i++)));
        }
        return vo;
    }
}
