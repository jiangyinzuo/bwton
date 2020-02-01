package pers.jiangyinzuo.carbon.dao.cache.impl;

import io.lettuce.core.api.sync.RedisCommands;
import org.springframework.stereotype.Repository;
import pers.jiangyinzuo.carbon.dao.cache.AbstractCache;
import pers.jiangyinzuo.carbon.dao.cache.CreditCache;
import pers.jiangyinzuo.carbon.dao.cache.KeyBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Jiang Yinzuo
 */
@Repository
public class CreditCacheImpl extends AbstractCache implements CreditCache {

    @Override
    public List<Object> getTotalCredits(Collection<Long> usersId) {
        RedisCommands<String, String> cmd = redisConnection.sync();
        List<Object> result = new ArrayList<>();
        for (Long id : usersId) {
            result.add(cmd.get(KeyBuilder.userCreditToday(id)));
            result.add(cmd.get(KeyBuilder.userCreditHistoryTotal(id)));
        }
        return result;
    }
}
