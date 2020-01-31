package pers.jiangyinzuo.carbon.dao.cache;

import java.util.Collection;
import java.util.List;

/**
 * @author Jiang Yinzuo
 */
public interface CreditCache {

    /**
     * 根据用户ID向缓存查询多个用户的总碳积分
     * @param usersId 用户ID集合
     * @return 用户碳积分
     */
    List<Object> getTotalCredits(Collection<Long> usersId);
}
