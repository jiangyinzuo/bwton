package pers.jiangyinzuo.carbon.dao.cache.impl;

import io.lettuce.core.LettuceFutures;
import io.lettuce.core.Range;
import io.lettuce.core.RedisFuture;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;
import pers.jiangyinzuo.carbon.dao.cache.BaseCache;
import pers.jiangyinzuo.carbon.dao.cache.CreditCache;
import pers.jiangyinzuo.carbon.domain.CREDIT_RECORD_MODE;
import pers.jiangyinzuo.carbon.domain.dto.PickCreditDropDTO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import static pers.jiangyinzuo.carbon.common.TimeUtil.getBeginOfThisWeekTimestamp;
import static pers.jiangyinzuo.carbon.dao.cache.RedisKeyBuilder.*;

/**
 * @author Jiang Yinzuo
 */
@Repository
@Log4j2
public class CreditCacheImpl extends BaseCache implements CreditCache {

    /**
     * 一周的时间间隔，单位毫秒
     */
    private static final long A_WEEK = 7 * 24 * 3600 * 1000L;

    /**
     * 碳积分收取记录保留条数
     */
    private static final long RECORDS_KEEP_COUNT = 7;

    @Override
    public List<Long> getUsersCredits(Collection<Long> usersId, CREDIT_RECORD_MODE mode) {

        List<Future<String>> futures = new ArrayList<>();
        for (Long id : usersId) {
            futures.add(cmdAsync.get(userCredit(id, mode.getMode())));
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
    public Long getUserCredit(Long userId, CREDIT_RECORD_MODE mode) {
        String credit = cmdSync.get(userCredit(userId, mode.getMode()));
        return credit == null ? 0 : Long.parseLong(credit);
    }

    @Override
    public List<String> getRawPickedRecord(Long userId) {
        return cmdSync.lrange(creditDropPickedRecord(userId), 0, RECORDS_KEEP_COUNT - 1);
    }

    @Override
    public void addCreditsAsync(Long userId, Integer credit) {

        // 用户碳积分累计总量、本周数量和剩余数量增加
        cmdAsync.incrby(userCredit(userId, CREDIT_RECORD_MODE.TOTAL.getMode()), credit);
        cmdAsync.incrby(userCredit(userId, CREDIT_RECORD_MODE.WEEK.getMode()), credit).thenRun(() -> cmdAsync.pexpireat(userCredit(userId, "week"), getBeginOfThisWeekTimestamp() + A_WEEK));
        cmdAsync.incrby(userCredit(userId, CREDIT_RECORD_MODE.REMAIN.getMode()), credit);
    }

    @Override
    public RedisFuture<Long> addCreditDropAsync(Long userId, Long credit, Long matureSpanMillis) {

        // 小水滴成熟的时间戳
        Long matureTimestamp = System.currentTimeMillis() + matureSpanMillis;

        String key = userCreditDrops(userId);
        RedisFuture<Long> future = cmdAsync.zadd(key, matureTimestamp.doubleValue(), getCreditDropValue(matureTimestamp, credit));

        // 为整个key设置过期时间
        cmdAsync.pexpire(key, matureSpanMillis + PickCreditDropDTO.EXPIRE_SPAN);

        // 移除已经过期的积分小水滴
        cmdAsync.zremrangebyscore(key, Range.create(0, System.currentTimeMillis() - PickCreditDropDTO.EXPIRE_SPAN));

        return future;
    }

    @Override
    public long getCreditDropsSize(Long userId) {
        return cmdSync.zcount(userCreditDrops(userId), Range.create(System.currentTimeMillis() - PickCreditDropDTO.EXPIRE_SPAN, Long.MAX_VALUE));
    }

    /**
     * @param matureTimestamp 小水滴成熟的时间戳
     * @param credit          小水滴积分值
     * @return 积分小水滴保存在redis有序列表中的value
     */
    private String getCreditDropValue(Long matureTimestamp, Long credit) {
        return matureTimestamp + "." + credit;
    }

    @Override
    public boolean removeCreditDrop(PickCreditDropDTO pickCreditDropDTO) {
        long removedCount = cmdSync.zrem(userCreditDrops(pickCreditDropDTO.getPickedUserId()), pickCreditDropDTO.getDropValue());

        // 该积分小水滴不存在
        if (removedCount == 0) {
            return false;
        }
        addPickedRecordAsync(pickCreditDropDTO);
        return true;
    }

    @Override
    public List<String> getCreditDrops(Long userId) {

        // 积分小水滴的最小未过期时间戳
        long minUnexpiredTimestamp = System.currentTimeMillis() - PickCreditDropDTO.EXPIRE_SPAN;
        return cmdSync.zrangebyscore(userCreditDrops(userId), Range.create(minUnexpiredTimestamp, Long.MAX_VALUE));
    }

    @Override
    public void addPickedRecordAsync(PickCreditDropDTO pickCreditDropDTO) {
        String dropPickedRecordKey = creditDropPickedRecord(pickCreditDropDTO.getPickedUserId());

        // 每次仅保留最新的7条记录
        cmdAsync.lpush(dropPickedRecordKey, pickCreditDropDTO.pickRecord()).thenRun(() -> cmdAsync.ltrim(dropPickedRecordKey, 0, RECORDS_KEEP_COUNT - 1));

    }
}
