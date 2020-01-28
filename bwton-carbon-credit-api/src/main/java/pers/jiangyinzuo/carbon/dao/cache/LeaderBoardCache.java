package pers.jiangyinzuo.carbon.dao.cache;

import pers.jiangyinzuo.carbon.domain.vo.LeaderBoardVO;

import java.util.List;

/**
 * @author Jiang Yinzuo
 */
public interface LeaderBoardCache {
    List<LeaderBoardVO> getLeaderBoard(Long userId);
}
