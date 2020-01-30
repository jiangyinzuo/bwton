package pers.jiangyinzuo.carbon.domain.vo;

import lombok.Data;
import pers.jiangyinzuo.carbon.domain.dto.LeaderBoardUserDTO;
import pers.jiangyinzuo.carbon.domain.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jiang Yinzuo
 */
@Data
public class LeaderBoardVO {
    private LeaderBoardUserDTO user;
    private List<LeaderBoardUserDTO> leaderboard;

    public LeaderBoardVO() {
        this.leaderboard = new ArrayList<>();
    }

    public void addUser(User user, Long credit) {
        leaderboard.add(new LeaderBoardUserDTO(user, credit));
    }
}
