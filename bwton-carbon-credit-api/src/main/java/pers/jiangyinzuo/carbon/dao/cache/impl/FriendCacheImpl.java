package pers.jiangyinzuo.carbon.dao.cache.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisZSetCommands;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.data.redis.support.collections.RedisZSet;
import org.springframework.stereotype.Repository;
import pers.jiangyinzuo.carbon.dao.cache.FriendCache;
import pers.jiangyinzuo.carbon.domain.dto.CreditDTO;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * @author Jiang Yinzuo
 */
@Repository
public class FriendCacheImpl implements FriendCache {

    private RedisTemplate<String, Object> redisTemplate;

    private static final byte[] LEADERBOARD_KEY = "bt:leaderboard".getBytes();

    @Autowired
    public void setRedisTemplate(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean addFriend(String userId1, String userId2) {
        byte[] user1Key = genUserFriKey(userId1).getBytes();
        byte[] user2Key = genUserFriKey(userId2).getBytes();
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

    @Override
    public List<CreditDTO> getFriendsCredit(Long userId) {
        byte[] userFriKey = genUserFriKey(userId.toString()).getBytes();
        List<Object> result = redisTemplate.executePipelined((RedisCallback<Object>)conn -> {
            conn.zSetCommands().zInterStore(
                    userFriKey,
                    RedisZSetCommands.Aggregate.SUM,
                    new int[] {0, 1},
                    userFriKey, LEADERBOARD_KEY);
            conn.zSetCommands().zRevRangeWithScores(userFriKey, 0, -1);
            conn.zSetCommands().zScore(LEADERBOARD_KEY, userId.toString().getBytes());
            return null;
        });
        List<CreditDTO> creditDTOList = new ArrayList<>();
        double userCredit = (double) result.get(2);
        @SuppressWarnings("unchecked")
        Set<ZSetOperations.TypedTuple<Number>> tupleSet = (Set<ZSetOperations.TypedTuple<Number>>) result.get(1);
        for (ZSetOperations.TypedTuple<Number> t : tupleSet) {
            if (t.getScore() == null) {
                creditDTOList.add(CreditDTO.createCreditDTO(t.getValue()));
            } else {
                if (t.getScore() <= userCredit) {
                    creditDTOList.add(new CreditDTO(userId, userCredit));
                    userCredit = -10D;
                }
                creditDTOList.add(CreditDTO.createCreditDTO(t));
            }
        }
        if (userCredit != -10D) {
            creditDTOList.add(new CreditDTO(userId, userCredit));
        }
        return creditDTOList;
    }

    private String genUserFriKey(String userId) {
        return "bt:user:" + userId + ":fri";
    }
}
