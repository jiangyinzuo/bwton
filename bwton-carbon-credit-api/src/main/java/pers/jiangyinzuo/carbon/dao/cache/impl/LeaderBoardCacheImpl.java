package pers.jiangyinzuo.carbon.dao.cache.impl;

import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import pers.jiangyinzuo.carbon.dao.cache.AbstractCache;
import pers.jiangyinzuo.carbon.dao.cache.LeaderBoardCache;
import pers.jiangyinzuo.carbon.domain.vo.LeaderBoardVO;

import java.util.ArrayList;
import java.util.List;

import static pers.jiangyinzuo.carbon.dao.cache.KeyUtil.*;

/**
 * @author Jiang Yinzuo
 */
public class LeaderBoardCacheImpl extends AbstractCache implements LeaderBoardCache {

    private void getFriendsInfoInPipeline(Long userId, RedisConnection conn) {


        conn.hashCommands().hGetAll(genUserInfoKeyBytes(userId));
    }

    /**
     * 在Redis中执行callback
     *
     * @param userId   用户ID
     * @param callback 需要执行的代码块
     * @return Redis执行结果
     */
    private List<Object> executeInRedis(Long userId, RedisCallback<Object> callback) {
        byte[] userFriKeyBytes = genUserFriKeyBytes(userId);

        Boolean hasKey = redisTemplate.execute(connection -> connection.exists(userFriKeyBytes), true);
        if (hasKey == null || !hasKey) {
            return new ArrayList<>(0);
        }

        return redisTemplate.executePipelined(callback);
    }

    @Override
    public List<LeaderBoardVO> getLeaderBoard(Long userId) {

        return null;
    }
}
