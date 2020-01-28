package pers.jiangyinzuo.carbon.service;

import pers.jiangyinzuo.carbon.domain.vo.LeaderBoardVO;

/**
 * @author Jiang Yinzuo
 */
public interface LeaderBoardService {

    /**
     * 获取碳积分排行榜上的好友积分、好友姓名、好友徽章数
     * @param userId 用户ID
     * @return LeaderBoardVO
     */
    LeaderBoardVO getLeaderBoardInfo(Long userId);
}
