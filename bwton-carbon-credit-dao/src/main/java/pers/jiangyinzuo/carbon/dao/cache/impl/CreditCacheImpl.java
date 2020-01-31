package pers.jiangyinzuo.carbon.dao.cache.impl;

import org.springframework.data.redis.core.RedisCallback;
import org.springframework.stereotype.Repository;
import pers.jiangyinzuo.carbon.dao.cache.AbstractCache;
import pers.jiangyinzuo.carbon.dao.cache.CreditCache;
import pers.jiangyinzuo.carbon.dao.cache.KeyBuilder;

import java.util.Collection;
import java.util.List;

/**
 * @author Jiang Yinzuo
 */
@Repository
public class CreditCacheImpl extends AbstractCache implements CreditCache {

    @Override
    public List<Object> getTotalCredits(Collection<Long> usersId) {

        return redisTemplate.executePipelined((RedisCallback<Object>) conn -> {
            for (Long id : usersId) {
                conn.get(KeyBuilder.userCreditTodayBytes(id));
                conn.get(KeyBuilder.userCreditHistoryTotalBytes(id));
            }
            return null;
        });
    }
}
