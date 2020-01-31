package pers.jiangyinzuo.carbon.dao.cache.impl;

import org.springframework.data.redis.core.RedisCallback;
import org.springframework.stereotype.Repository;
import pers.jiangyinzuo.carbon.dao.cache.AbstractCache;
import pers.jiangyinzuo.carbon.dao.cache.FriendCache;
import pers.jiangyinzuo.carbon.dao.cache.KeyBuilder;

import java.util.Set;

import static pers.jiangyinzuo.carbon.dao.cache.KeyBuilder.userFri;

/**
 * @author Jiang Yinzuo
 */
@Repository
public class FriendCacheImpl extends AbstractCache implements FriendCache {

    @Override
    public void delUserFriKey(Long ...userId) {
        byte[][] userFriKeys = KeyBuilder.multiUserFriBytes(userId);
        redisTemplate.executePipelined((RedisCallback<Object>) conn -> {
            conn.del(userFriKeys);
            return null;
        });
    }

    @Override
    public Set<Object> getFriendsId(Long userId) {
        // 缓存失效
        if (Boolean.FALSE.equals(redisTemplate.hasKey(userFri(userId)))) {
            return null;
        }
        // 缓存命中
        return redisTemplate.opsForSet().members(userFri(userId));
    }

    @Override
    public void setFriendsId(Long userId, Set<Long> friendsId) {
        redisTemplate.opsForSet().add(userFri(userId), friendsId.toArray());
    }
}
