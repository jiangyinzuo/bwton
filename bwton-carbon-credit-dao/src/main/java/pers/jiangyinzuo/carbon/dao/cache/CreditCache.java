package pers.jiangyinzuo.carbon.dao.cache;

import java.util.Collection;
import java.util.List;

/**
 * @author Jiang Yinzuo
 */
public interface CreditCache {

    /**
     * 查询多个用户的总碳积分
     * @param usersId 用户ID集合
     * @param span 时间间隔 total, today, week, remain
     * @return 用户碳积分
     */
    List<Long> getCredits(Collection<Long> usersId, String span);

    /**
     * 获取好友碳积分被收取记录
     * @param friendId 好友ID
     * @return 收取列表, 负数表示用户收取、正数表示好友收取
     */
    List<List<Integer>> getCreditCollectedRecord(Long friendId);

    /**
     * 收取碳积分
     * @param userId 用户ID
     * @param collectedUserId 被收取的好友ID
     * @param credit 收取的碳积分
     */
    void collectCredits(Long userId, Long collectedUserId, Integer credit);

    /**
     * 增加积分小水滴
     * @param userId 用户ID
     * @param credit 小水滴数值
     * @param matureSpanMillis 水滴成熟时间间隔
     */
    void addCreditDrop(Long userId, Long credit, Long matureSpanMillis);

    /**
     * 移除积分小水滴
     * @param pickedUserId 被采摘者用户ID
     * @param value        小水滴的value
     * @return 1: 采摘成功; 0: 小水滴不存在;
     */
    Long removeCreditDrop(Long pickedUserId, String value);
}
