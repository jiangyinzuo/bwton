package pers.jiangyinzuo.carbon.dao.cache.impl;

import org.springframework.data.redis.core.RedisCallback;
import org.springframework.stereotype.Repository;
import pers.jiangyinzuo.carbon.dao.cache.AbstractCache;
import pers.jiangyinzuo.carbon.dao.cache.KeyBuilder;
import pers.jiangyinzuo.carbon.dao.cache.UserCache;
import pers.jiangyinzuo.carbon.domain.entity.User;

import java.util.List;
import java.util.Set;

import static pers.jiangyinzuo.carbon.dao.cache.KeyBuilder.userInfoBytes;

/**
 * @author Jiang Yinzuo
 */
@Repository
public class UserCacheImpl extends AbstractCache implements UserCache {
    @Override
    public void setUsersAsync(List<Object> users) {
        redisTemplate.executePipelined((RedisCallback<Object>) conn -> {
            for (Object user : users) {
                conn.hashCommands().hMSet(
                        KeyBuilder.userInfoBytes(((User)user).getUserId()),
                        ((User)user).getHash()
                        );
            }
            return null;
        });
        System.out.println("设置完成！");
    }

    @Override
    public List<Object> getUsers(Set<Long> userIds) {
        return redisTemplate.executePipelined((RedisCallback<User>) conn -> {
            for (Long id : userIds) {
                conn.hashCommands().hGetAll(userInfoBytes(id));
            }
            return null;
        });
    }
}
