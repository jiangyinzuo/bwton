package pers.jiangyinzuo.carbon.dao.cache.impl;

import io.lettuce.core.RedisFuture;
import org.springframework.stereotype.Repository;
import pers.jiangyinzuo.carbon.dao.cache.AbstractCache;
import pers.jiangyinzuo.carbon.dao.cache.KeyBuilder;
import pers.jiangyinzuo.carbon.dao.cache.UserCache;
import pers.jiangyinzuo.carbon.domain.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Future;

import static pers.jiangyinzuo.carbon.dao.cache.KeyBuilder.userInfo;

/**
 * @author Jiang Yinzuo
 */
@Repository
public class UserCacheImpl extends AbstractCache implements UserCache {
    @Override
    public void setUsersAsync(List<User> users) {
        for (User user : users) {
            redisConnection.async().hmset(
                    KeyBuilder.userInfo(user.getUserId()),
                    user.getHash()
            );
        }
    }

    @Override
    public List<Map<String, String>> getUsers(Set<Long> userIds) {
        List<Map<String, String>> result = new ArrayList<>();
        for (Long id : userIds) {
            result.add(redisConnection.sync().hgetall(userInfo(id)));
        }
        return result;
    }
}
