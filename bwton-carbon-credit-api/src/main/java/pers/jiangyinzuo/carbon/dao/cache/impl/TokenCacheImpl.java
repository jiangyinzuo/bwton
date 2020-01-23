package pers.jiangyinzuo.carbon.dao.cache.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;
import pers.jiangyinzuo.carbon.dao.cache.TokenCache;

import java.util.concurrent.TimeUnit;

/**
 * @author Jiang Yinzuo
 */
@Repository
public class TokenCacheImpl implements TokenCache {

    private ValueOperations<String, String> valueOperations;

    @Autowired
    public void setValueOperations(RedisTemplate<String, String> redisTemplate) {
        this.valueOperations = redisTemplate.opsForValue();
    }

    @Override
    public void setSignature(String userId, String signature) {
        valueOperations.set(getKeyName(userId), signature, 10, TimeUnit.DAYS);
    }

    @Override
    public String getSignature(String userId) {
        return valueOperations.get(getKeyName(userId));
    }

    private String getKeyName(String userId) {
        return "bt:user:" + userId + ":sk";
    }
}
