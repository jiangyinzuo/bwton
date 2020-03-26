package pers.jiangyinzuo.carbon.service;

import pers.jiangyinzuo.carbon.domain.CREDIT_RECORD_MODE;
import pers.jiangyinzuo.carbon.domain.dto.PickCreditDropDTO;
import pers.jiangyinzuo.carbon.domain.vo.LeaderBoardVO;
import pers.jiangyinzuo.carbon.domain.vo.PickedRecordVO;

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
    LeaderBoardVO getLeaderBoard(Long userId, CREDIT_RECORD_MODE mode);

    /**
     * 获取用户的积分小水滴
     * @param userId userId
     * @return 小水滴列表, 每个字符串由{UNIX时间戳}.{水滴数值}组成
     */
    List<String> getCreditDrops(Long userId);

    /**
     * 获取用户的碳积分
     * @param userId 用户ID
     * @param mode 统计方式
     * @return 用户碳积分
     */
    Long getUserCredit(Long userId, CREDIT_RECORD_MODE mode);

    /**
     * 采摘碳积分
     * @param pickCreditDropDTO 碳积分小水滴实体类
     * @return 是否采摘成功
     */
    boolean pickCreditDrop(PickCreditDropDTO pickCreditDropDTO);

    /**
     * 获取用户被采摘碳积分的最近7条记录
     * @param queriedUserId 被查询者ID
     * @return
     */
    List<PickedRecordVO> getPickedRecord(Long queriedUserId);

    long getCoveredTime(Long userId);
}
