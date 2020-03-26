package pers.jiangyinzuo.carbon.dao.cache;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pers.jiangyinzuo.carbon.dao.cache.RedisKeyBuilder.userCreditDrops;
import static pers.jiangyinzuo.carbon.domain.CREDIT_RECORD_MODE.REMAIN;
import static pers.jiangyinzuo.carbon.domain.CREDIT_RECORD_MODE.TOTAL;

public class CreditCacheTest extends AbstractCacheTest {
    private CreditCache creditCache;

    @Autowired
    public void setCreditCache(CreditCache creditCache) {
        this.creditCache = creditCache;
    }

    @Test
    public void testGetTotalCredit() {
        var result = creditCache.getUsersCredits(List.of(1L, 2L), TOTAL);
        assertEquals(2, result.size());
    }

    /**
     * 测试生成积分小水滴
     */
    @Test
    public void testAddCreditDrop() {
        final long userId = 10000L;
        conn.sync().del(userCreditDrops(userId));
        var future1 = creditCache.addCreditDropAsync(userId, 40L, 4000L);
        var future2 = creditCache.addCreditDropAsync(userId, 40L, 4000L);

        try {
            future1.await(1, TimeUnit.SECONDS);
            future2.await(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        var result = creditCache.getCreditDrops(userId);
        assertEquals(2, result.size());
        conn.sync().del(userCreditDrops(userId));
    }

    /**
     * 测试增加碳积分
     */
    @Test
    public void testAddCredit() {
        final long pickerUserId = 2L;
        long remainCreditBefore = creditCache.getUserCredit(2L, REMAIN);
        creditCache.addCreditsAsync(pickerUserId, 3);
        long remainCredit = creditCache.getUserCredit(2L, REMAIN);

        assertEquals(3, remainCredit - remainCreditBefore);
    }
}
