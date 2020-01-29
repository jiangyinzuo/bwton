package pers.jiangyinzuo.carbon.domain.vo;

import lombok.Data;
import pers.jiangyinzuo.carbon.domain.dto.LeaderBoardUserInfoDTO;

import java.util.List;

/**
 * @author Jiang Yinzuo
 */
@Data
public class LeaderBoardVO {
    private LeaderBoardUserInfoDTO userInfo;
    private List<LeaderBoardUserInfoDTO> leaderBoardUsersInfo;
}
