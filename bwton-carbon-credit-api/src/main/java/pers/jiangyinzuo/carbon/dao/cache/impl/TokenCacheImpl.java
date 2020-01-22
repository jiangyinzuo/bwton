package pers.jiangyinzuo.carbon.dao.cache.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import pers.jiangyinzuo.carbon.dao.cache.TokenCache;

/**
 * @author Jiang Yinzuo
 */
@Repository
public class TokenCacheImpl implements TokenCache {

    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean validateToken(Long userId, String token) {
        return false;
    }

    @Override
    public void setToken(Long userId, String token) {
        redisTemplate.opsForValue().set("bt:token:"+userId, token);
    }
}
