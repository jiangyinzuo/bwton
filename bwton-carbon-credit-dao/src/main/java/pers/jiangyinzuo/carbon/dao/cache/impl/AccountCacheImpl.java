package pers.jiangyinzuo.carbon.dao.cache.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;
import pers.jiangyinzuo.carbon.dao.cache.AccountCache;

import static pers.jiangyinzuo.carbon.dao.cache.KeyBuilder.USER_TOTAL;

/**
 * @author Jiang Yinzuo
 */
@Repository
public class AccountCacheImpl implements AccountCache {

    private ValueOperations<String, Object> valueOperations;

    @Autowired
    public void setValueOperations(RedisTemplate<String, Object> redisTemplate) {
        valueOperations = redisTemplate.opsForValue();
    }

    @Override
    public void increaseAccountTotal() {
        valueOperations.increment(USER_TOTAL, 1);
    }

    @Override
    public long getAccountTotal() {
        Object total = valueOperations.get(USER_TOTAL);
        return total == null ? 0 : ((Number)total).longValue();
    }
}
