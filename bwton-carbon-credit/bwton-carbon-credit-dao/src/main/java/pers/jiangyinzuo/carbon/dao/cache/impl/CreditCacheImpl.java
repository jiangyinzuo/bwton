package pers.jiangyinzuo.carbon.dao.cache.impl;

import io.lettuce.core.LettuceFutures;
import io.lettuce.core.Range;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.ScoredValue;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;
import pers.jiangyinzuo.carbon.dao.cache.BaseCache;
import pers.jiangyinzuo.carbon.dao.cache.CreditCache;
import pers.jiangyinzuo.carbon.domain.entity.CreditDrop;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static pers.jiangyinzuo.carbon.common.TimeUtil.getTodayTimestamp;
import static pers.jiangyinzuo.carbon.dao.cache.KeyBuilder.*;

/**
 * @author Jiang Yinzuo
 */
@Repository
@Log4j2
public class CreditCacheImpl extends BaseCache implements CreditCache {

    @Override
    public List<Long> getUsersCredits(Collection<Long> usersId, String mode) {

        List<Future<String>> futures = new ArrayList<>();
        for (Long id : usersId) {
            futures.add(cmdAsync.get(userCredit(id, mode)));
        }
        LettuceFutures.awaitAll(10, TimeUnit.SECONDS, futures.toArray(new Future[0]));
        List<Long> result = new ArrayList<>(futures.size());

        try {
            String credit;
            for (Future<String> future : futures) {
                credit = future.get();
                result.add(credit == null ? 0 : Long.parseLong(credit));
            }
        } catch (InterruptedException | ExecutionException e) {
            log.error("error");
            Thread.currentThread().interrupt();
        }
        return result;
    }

    @Override
    public List<List<Integer>> getCreditCollectedRecord(Long friendId) {
        List<RedisFuture<List<String>>> futures = new ArrayList<>(7);
        for (int i = 1; i <= 7; ++i) {
            futures.add(cmdAsync.lrange(userCreditRecord(friendId, i), 0, -1));
        }
        List<List<Integer>> collectedRecords = new ArrayList<>(7);

        LettuceFutures.awaitAll(10, TimeUnit.SECONDS, futures.toArray(new RedisFuture[0]));
        int i = 0;
        for (var future : futures) {
            List<Integer> record = new ArrayList<>(7);
            try {
                List<String> recordStr = future.get();
                for (var str : recordStr) {
                    record.add(Integer.parseInt(str));
                }
            } catch (InterruptedException | ExecutionException e) {
                Thread.currentThread().interrupt();
            }
            collectedRecords.set(i++, record);
        }
        return collectedRecords;
    }

    @Override
    public void pickCreditsAsync(Long userId, Long collectedUserId, Integer credit) {
        cmdAsync.incrby(userCredit(userId, "total"), credit);
        cmdAsync.incrby(userCredit(userId, "today"), credit);
        cmdAsync.incrby(userCredit(userId, "week"), credit);

        int dayOfWeek = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);

        String userCreditRecord = userCreditRecord(userId, dayOfWeek);
        cmdAsync.rpush(userCreditRecord, credit.toString()).thenRun(() -> cmdAsync.expireat(userCreditRecord, getTodayTimestamp()));
    }

    @Override
    public RedisFuture<Long> addCreditDropAsync(Long userId, Long credit, Long matureSpanMillis) {

        // 小水滴成熟的时间戳
        Long matureTimestamp = System.currentTimeMillis() + matureSpanMillis;

        String key = userCreditDrops(userId);
        RedisFuture<Long> future = cmdAsync.zadd(key,  matureTimestamp.doubleValue() , getCreditDropValue(matureTimestamp, credit));

        // 为整个key设置过期时间
        cmdAsync.pexpire(key, matureSpanMillis + CreditDrop.EXPIRE_SPAN);

        // 移除已经过期的积分小水滴
        cmdAsync.zremrangebyscore(key, Range.create(0, System.currentTimeMillis() - CreditDrop.EXPIRE_SPAN));

        return future;
    }

    @Override
    public long getCreditDropsSize(Long userId) {
        return cmdSync.zcount(userCreditDrops(userId), Range.create(System.currentTimeMillis() - CreditDrop.EXPIRE_SPAN, Long.MAX_VALUE));
    }

    /**
     *
     * @param matureTimestamp 小水滴成熟的时间戳
     * @param credit 小水滴积分值
     * @return 积分小水滴保存在redis有序列表中的value
     */
    private String getCreditDropValue(Long matureTimestamp, Long credit) {
        return matureTimestamp + "." + credit;
    }

    @Override
    public Long removeCreditDrop(Long pickedUserId, String value) {
        return cmdSync.zrem(userCreditDrops(pickedUserId), value);
    }

    @Override
    public List<ScoredValue<String>> getCreditDrops(Long userId) {

        // 积分小水滴的最小未过期时间戳
        long minUnexpiredTimestamp = System.currentTimeMillis() - CreditDrop.EXPIRE_SPAN;
        return cmdSync.zrangebyscoreWithScores(userCreditDrops(userId), Range.create(minUnexpiredTimestamp, Long.MAX_VALUE));
    }
}
