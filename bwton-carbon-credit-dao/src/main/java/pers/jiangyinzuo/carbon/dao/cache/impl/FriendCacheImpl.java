package pers.jiangyinzuo.carbon.dao.cache.impl;

import org.springframework.data.redis.core.RedisCallback;
import org.springframework.stereotype.Repository;
import pers.jiangyinzuo.carbon.dao.cache.AbstractCache;
import pers.jiangyinzuo.carbon.dao.cache.FriendCache;
import pers.jiangyinzuo.carbon.dao.cache.KeyUtil;

import java.util.Set;

import static pers.jiangyinzuo.carbon.dao.cache.KeyUtil.*;

/**
 * @author Jiang Yinzuo
 */
@Repository
public class FriendCacheImpl extends AbstractCache implements FriendCache {

    @Override
    public boolean addFriend(Long userId1, Long userId2) {
        byte[] user1Key = KeyUtil.genUserFriKeyBytes(userId1);
        byte[] user2Key = KeyUtil.genUserFriKeyBytes(userId2);
        Boolean result = redisTemplate.execute((RedisCallback<Boolean>) conn -> {
            byte[] totalBytes = conn.get("bt:user:total".getBytes());
            if (totalBytes == null) {
                return false;
            }
            conn.openPipeline();
            long total = Long.parseLong(new String(totalBytes));
            if (total >= userId1 && total >= userId2) {
                conn.zSetCommands().zAdd(user1Key, 0, userId2.toString().getBytes());
                conn.zSetCommands().zAdd(user2Key, 0, userId1.toString().getBytes());
                conn.expire(user1Key, FRIENDS_EXPIRE_TIME);
                conn.expire(user2Key, FRIENDS_EXPIRE_TIME);
                return true;
            }
            return false;
        });
        return result != null && result;
    }

    @Override
    public Set<Object> getFriendsId(Long userId) {
        // 缓存失效
        if (!redisTemplate.hasKey(genUserFriKey(userId))) {
            return null;
        }
        // 缓存命中
        return redisTemplate.opsForSet().members(genUserFriKey(userId));
    }

    @Override
    public void setFriendsId(Long userId, Set<Long> friendsId) {
        redisTemplate.opsForSet().add(genUserFriKey(userId), friendsId.toArray());
    }
}
