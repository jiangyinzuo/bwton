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
     */
    void collectCredits(Long userId, Long collectedUserId, Integer credit);
}
