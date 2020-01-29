package pers.jiangyinzuo.carbon.dao.cache.impl;

import org.springframework.data.redis.core.RedisCallback;
import org.springframework.stereotype.Repository;
import pers.jiangyinzuo.carbon.dao.cache.AbstractCache;
import pers.jiangyinzuo.carbon.dao.cache.KeyUtil;
import pers.jiangyinzuo.carbon.dao.cache.LeaderBoardCache;

import java.util.List;
import java.util.Set;

/**
 * @author Jiang Yinzuo
 */
@Repository
public class LeaderBoardCacheImpl extends AbstractCache implements LeaderBoardCache {

    @Override
    public List<Object> getLeaderBoard(Set<Long> usersId) {
        return redisTemplate.executePipelined((RedisCallback<Object>) conn -> {
            for (Long id : usersId) {
                conn.hashCommands().hGetAll(KeyUtil.genUserInfoKeyBytes(id));
            }
            return null;
        });
    }
}
