package pers.jiangyinzuo.carbon.dao.cache;

import io.lettuce.core.RedisFuture;
import io.lettuce.core.ScoredValue;

import java.util.Collection;
import java.util.List;

/**
 * 积分小水滴以 value=成熟时间戳.水滴数值, score=成熟时间戳 的形式存在Redis有序集合中。
 * 每个小水滴过期时间固定
 *
 * @author Jiang Yinzuo
 */
public interface CreditCache {

    /**
     * 查询多个用户的总碳积分
     * @param usersId 用户ID集合
     * @param mode 时间间隔 total, week, remain
     * @return 用户碳积分列表（不按积分大小排序，同给定的用户ID集合顺序一致）
     */
    List<Long> getUsersCredits(Collection<Long> usersId, String mode);

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
    void pickCreditsAsync(Long userId, Long collectedUserId, Integer credit);

    /**
     * 增加积分小水滴
     * @param userId 用户ID
     * @param credit 小水滴数值
     * @param matureSpanMillis 水滴成熟时间间隔
     */
    RedisFuture<Long> addCreditDropAsync(Long userId, Long credit, Long matureSpanMillis);

    /**
     * 获取一个用户当前积分小水滴个数
     * @param userId 用户ID
     * @return
     */
    long getCreditDropsSize(Long userId);

    /**
     * 移除积分小水滴
     * @param pickedUserId 被采摘者用户ID
     * @param value        小水滴的value
     * @return 1: 采摘成功; 0: 小水滴不存在;
     */
    Long removeCreditDrop(Long pickedUserId, String value);

    /**
     * 获取用户的积分小水滴
     * @param userId 用户ID
     * @return 积分小水滴列表
     */
    List<ScoredValue<String>> getCreditDrops(Long userId);
}
