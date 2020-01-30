package pers.jiangyinzuo.carbon.dao.cache;



import org.apache.commons.lang3.tuple.Pair;

import java.util.Collection;
import java.util.List;

/**
 * @author Jiang Yinzuo
 */
public interface CreditCache {
    /**
     * 增加碳积分
     * @param userId 用户ID
     * @param creditDelta 增量
     */
    @Deprecated
    void addCredit(String userId, long creditDelta);

    /**
     * 减少碳积分
     * @param userId 用户ID
     * @param creditDelta 减量
     */
    @Deprecated
    void subCredit(String userId, long creditDelta);

    /**
     * 查询用户的碳积分数量
     * @param userId 用户ID
     * @return 碳积分
     */
    @Deprecated
    Long getCredit(String userId);

    /**
     * 根据用户ID向缓存查询多个用户的总碳积分
     * @param usersId 用户ID集合
     * @return 用户碳积分
     */
    List<Object> getTotalCredits(Collection<Long> usersId);
}
