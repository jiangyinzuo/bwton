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
        if (!redisTemplate.hasKey(genUserFriKey(userId))) {
            return null;
        }
        return redisTemplate.opsForSet().members(genUserFriKey(userId));
    }

    @Override
    public void setFriendsId(Long userId, Set<Long> friendsId) {
        byte[][] friends = new byte[friendsId.size()][];
        int i = 0;
        for (Object friendId : friendsId) {
            friends[i++] = friendId.toString().getBytes();
        }
        redisTemplate.executePipelined((RedisCallback<Object>) conn -> {
            conn.setCommands().sAdd(genUserFriKeyBytes(userId), friends);
            return null;
        });
    }
}
