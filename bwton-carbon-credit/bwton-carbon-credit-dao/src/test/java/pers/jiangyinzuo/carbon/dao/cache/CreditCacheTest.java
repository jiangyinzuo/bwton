package pers.jiangyinzuo.carbon.dao.cache;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static pers.jiangyinzuo.carbon.dao.cache.KeyBuilder.userCreditDrops;

@SpringBootTest
public class CreditCacheTest extends AbstractCacheTest {
    private CreditCache creditCache;

    @Autowired
    public void setCreditCache(CreditCache creditCache) {
        this.creditCache = creditCache;
    }

    @Test
    public void testGetTotalCredit() {
        var result = creditCache.getUsersCredits(List.of(1L, 2L), "total");
        assertEquals(2, result.size());
    }

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
}
