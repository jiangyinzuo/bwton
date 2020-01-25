package pers.jiangyinzuo.carbon.dao.cache.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import pers.jiangyinzuo.carbon.dao.cache.FriendCache;

/**
 * @author Jiang Yinzuo
 */
@Repository
public class FriendCacheImpl implements FriendCache {

    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean addFriend(String userId1, String userId2) {
        byte[] user1Key = getKeyName(userId1).getBytes();
        byte[] user2Key = getKeyName(userId2).getBytes();
        Boolean result = redisTemplate.execute((RedisCallback<Boolean>) conn -> {
            byte[] totalBytes = conn.get("bt:user:total".getBytes());
            if (totalBytes == null) {
                return false;
            }
            conn.openPipeline();
            long total = Long.parseLong(new String (totalBytes));
            if (total >= Long.parseLong(userId1) && total >= Long.parseLong(userId2)) {
                conn.zSetCommands().zAdd(user1Key, 0, userId2.getBytes());
                conn.zSetCommands().zAdd(user2Key, 0, userId1.getBytes());
                conn.expire(user1Key, FRIENDS_EXPIRE_TIME);
                conn.expire(user2Key, FRIENDS_EXPIRE_TIME);
                return true;
            }
            return false;
        });
        return result != null && result;
    }

    private String getKeyName(String userId) {
        return "bt:user:" + userId + ":fri";
    }
}
