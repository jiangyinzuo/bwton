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
}
