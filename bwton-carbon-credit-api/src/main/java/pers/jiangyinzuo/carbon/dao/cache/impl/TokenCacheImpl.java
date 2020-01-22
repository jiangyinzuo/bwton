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
    public boolean validateToken(Long userId, String token) {
        String value = valueOperations.get("bt:token:" + userId);
        if (value == null) {
            return false;
        }
        return value.equals(token);
    }

    @Override
    public void setToken(Long userId, String token) {
        valueOperations.set("bt:token:" + userId, token, 10, TimeUnit.DAYS);
    }
}
