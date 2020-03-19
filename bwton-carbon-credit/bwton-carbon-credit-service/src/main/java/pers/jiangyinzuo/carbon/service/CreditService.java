package pers.jiangyinzuo.carbon.service;

import io.lettuce.core.ScoredValue;
import pers.jiangyinzuo.carbon.domain.vo.LeaderBoardVO;

import java.util.List;

/**
 * @author Jiang Yinzuo
 */
public interface CreditService {

    /**
     * 获取碳积分排行榜上的好友积分、好友姓名、好友徽章数
     * @param userId 用户ID
     * @param mode 模式, total: 总榜; week: 周榜
     * @return LeaderBoardVO
     */
    LeaderBoardVO getLeaderBoard(Long userId, String mode);

    /**
     * 获取用户的积分小水滴
     * @param userId userId
     * @return 小水滴列表, 每个字符串由{UNIX时间戳}.{水滴数值}组成
     */
    List<ScoredValue<String>> getCreditDrops(Long userId);
}
