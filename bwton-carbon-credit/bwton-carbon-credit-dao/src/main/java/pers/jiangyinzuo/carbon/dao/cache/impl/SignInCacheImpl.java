package pers.jiangyinzuo.carbon.dao.cache.impl;

import io.lettuce.core.RedisFuture;
import org.springframework.stereotype.Repository;
import pers.jiangyinzuo.carbon.common.TimeUtil;
import pers.jiangyinzuo.carbon.dao.cache.BaseCache;
import pers.jiangyinzuo.carbon.dao.cache.SignInCache;

import java.util.List;

import static pers.jiangyinzuo.carbon.dao.cache.RedisKeyBuilder.*;

/**
 * @author Jiang Yinzuo
 */
@Repository
public class SignInCacheImpl extends BaseCache implements SignInCache {

    @Override
    public RedisFuture<String> getSignInCalendarAsync(Long userId) {
        return cmdAsync.get(userSignInCalendar(userId));
    }

    @Override
    public void updateSignInCalendarAsync(Long userId, int newBitCalendar) {
        String key = userSignInCalendar(userId);

        cmdAsync.set(key, String.valueOf(newBitCalendar));

        //该key在月底过期
        cmdAsync.expireat(key, TimeUtil.getBeginOfNextMonthTimestamp() - 1);
    }

    @Override
    public RedisFuture<List<String>> getReSignInDaysAsync(Long userId) {
        return cmdAsync.lrange(userSignInRe(userId), 0, -1);
    }

    @Override
    public RedisFuture<String> getContinueSignInDaysAsync(Long userId) {
        return cmdAsync.get(userSignInContinue(userId));
    }

    @Override
    public RedisFuture<String> getLastContinueSignInDaysAsync(Long userId) {
        return cmdAsync.get(userSignInLast(userId));
    }

    @Override
    public void addContinueDaysAsync(Long userId, long days) {
        cmdAsync.incrby(userSignInContinue(userId), days);
    }

    @Override
    public void updateLastContinueDaysAsync(Long userId, int continueDaysBeforeSignIn) {
        cmdAsync.set(userSignInContinue(userId), String.valueOf(continueDaysBeforeSignIn));
    }
}
