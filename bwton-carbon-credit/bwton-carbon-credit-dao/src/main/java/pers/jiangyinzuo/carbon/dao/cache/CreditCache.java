package pers.jiangyinzuo.carbon.dao.cache;

import io.lettuce.core.RedisFuture;
import pers.jiangyinzuo.carbon.domain.CREDIT_RECORD_MODE;
import pers.jiangyinzuo.carbon.domain.dto.PickCreditDropDTO;

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
     * @param mode 积分统计方式 total, week, remain
     * @return 用户碳积分列表（不按积分大小排序，同给定的用户ID集合顺序一致）
     */
    List<Long> getUsersCredits(Collection<Long> usersId, CREDIT_RECORD_MODE mode);

    /**
     * 获取用户碳积分
     * @param userId 用户ID
     * @param mode 积分统计方式 total, week, remain
     * @return 用户碳积分
     */
    Long getUserCredit(Long userId, CREDIT_RECORD_MODE mode);

    /**
     * 获取好友碳积分被收取记录
     * @param userId 用户ID
     * @return 收取列表，Redis列表值的原始数据
     */
    List<String> getRawPickedRecord(Long userId);

    /**
     * 增加碳积分：缓存中用户碳积分收集总量和本月数量增加，并添加碳积分收取记录
     *
     * @param userId 用户ID
     * @param credit 增加的碳积分数值
     */
    void addCreditsAsync(Long userId, Integer credit);

    /**
     * 添加积分小水滴
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
     * @param pickCreditDropDTO 积分小水滴实体类
     * @return 1: 采摘成功; 0: 小水滴不存在;
     */
    boolean removeCreditDrop(PickCreditDropDTO pickCreditDropDTO);

    /**
     * 获取用户的积分小水滴
     * @param userId 用户ID
     * @return 积分小水滴列表
     */
    List<String> getCreditDrops(Long userId);

    /**
     * 添加采摘积分小水滴记录
     * @param pickCreditDropDTO
     */
    void addPickedRecordAsync(PickCreditDropDTO pickCreditDropDTO);
}
