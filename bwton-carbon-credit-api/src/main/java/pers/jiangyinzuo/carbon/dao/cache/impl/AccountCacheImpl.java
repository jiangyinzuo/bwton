package pers.jiangyinzuo.carbon.dao.cache.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;
import pers.jiangyinzuo.carbon.dao.cache.AccountCache;

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
        valueOperations.increment("bt:user:total", 1);
    }

    @Override
    public long getAccountTotal() {
        Object total = valueOperations.get("bt:user:total");
        if (total == null) {
            return 0;
        }
        if (total instanceof Integer) {
            return ((Integer) total).longValue();
        }
        return (Long) total;
    }
}
