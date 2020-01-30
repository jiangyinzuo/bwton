package pers.jiangyinzuo.carbon.dao.cache.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;
import pers.jiangyinzuo.carbon.dao.cache.AbstractCache;
import pers.jiangyinzuo.carbon.dao.cache.CreditCache;
import pers.jiangyinzuo.carbon.dao.cache.KeyBuilder;

import java.util.Collection;
import java.util.List;

/**
 * @author Jiang Yinzuo
 */
@Repository
public class CreditCacheImpl extends AbstractCache implements CreditCache {

    private ValueOperations<String, Object> valueOperations;

    @Autowired
    public void setValueOperations(ValueOperations<String, Object> valueOperations) {
        this.valueOperations = valueOperations;
    }

    @Override
    public void addCredit(String userId, long creditDelta) {
        valueOperations.increment(getKeyName(userId), creditDelta);
    }

    @Override
    public void subCredit(String userId, long creditDelta) {
        valueOperations.decrement(getKeyName(userId), creditDelta);
    }

    @Override
    public Long getCredit(String userId) {
        Object result = valueOperations.get(getKeyName(userId));
        if (result == null) {
            return 0L;
        }
        return Long.parseLong(result.toString());
    }

    @Override
    public List<Object> getTotalCredits(Collection<Long> usersId) {

        return redisTemplate.executePipelined((RedisCallback<Object>) conn -> {
            for (Long id : usersId) {
                conn.get(KeyBuilder.userCreditTodayBytes(id));
                conn.get(KeyBuilder.userCreditHistoryTotalBytes(id));
            }
            return null;
        });
    }

    @Deprecated
    private String getKeyName(String userId) {
        return "bt:user:" + userId + ":credit";
    }
}
