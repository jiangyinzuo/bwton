package pers.jiangyinzuo.carbon.dao.cache;

/**
 * @author Jiang Yinzuo
 */
public interface CreditCache {
    /**
     * 增加碳积分
     * @param userId 用户ID
     * @param creditDelta 增量
     */
    void addCredit(String userId, long creditDelta);

    /**
     * 减少碳积分
     * @param userId 用户ID
     * @param creditDelta 减量
     */
    void subCredit(String userId, long creditDelta);

    /**
     * 查询用户的碳积分数量
     * @param userId 用户ID
     * @return 碳积分
     */
    Long getCredit(String userId);
}
