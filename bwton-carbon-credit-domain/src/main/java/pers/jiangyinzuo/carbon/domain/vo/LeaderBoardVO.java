package pers.jiangyinzuo.carbon.domain.vo;

import lombok.Data;
import pers.jiangyinzuo.carbon.domain.dto.LeaderBoardUserDTO;
import pers.jiangyinzuo.carbon.domain.entity.User;

import java.util.Set;
import java.util.TreeSet;

/**
 * @author Jiang Yinzuo
 */
@Data
public class LeaderBoardVO {
    private LeaderBoardUserDTO user;
    private Set<LeaderBoardUserDTO> leaderboard;

    public LeaderBoardVO() {
        this.leaderboard = new TreeSet<>();
    }

    public void addUser(User user, Long credit) {
        leaderboard.add(new LeaderBoardUserDTO(user, credit));
    }
}
