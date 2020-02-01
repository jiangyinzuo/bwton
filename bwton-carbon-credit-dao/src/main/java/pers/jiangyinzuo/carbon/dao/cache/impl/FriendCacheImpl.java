package pers.jiangyinzuo.carbon.dao.cache.impl;

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
        String[] userFriKeys = KeyBuilder.multiUserFri(userId);
        redisConnection.sync().del(userFriKeys);
    }

    @Override
    public Set<String> getFriendsId(Long userId) {
        // 缓存失效
        if (redisConnection.sync().exists(userFri(userId)) != 1L) {
            return null;
        }
        // 缓存命中
        return redisConnection.sync().smembers(userFri(userId));
    }

    @Override
    public void setFriendsId(Long userId, Set<Long> friendsId) {
        String[] arr = new String[friendsId.size()];
        int i = 0;
        for (Long id : friendsId) {
            arr[i++] = id.toString();
        }
        redisConnection.sync().sadd(userFri(userId), arr);
    }
}
