package pers.jiangyinzuo.carbon.dao.cache;

import io.lettuce.core.RedisFuture;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pers.jiangyinzuo.carbon.common.StringUtil;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static pers.jiangyinzuo.carbon.dao.cache.RedisKeyBuilder.userSignInRe;

public class SignInCacheTest extends AbstractCacheTest {

    @Autowired
    private SignInCache signInCache;

    @Test
    public void testUpdateSignInCalendarAsync() throws InterruptedException, ExecutionException, TimeoutException {
        Integer calendar = StringUtil.toInteger(signInCache.getSignInCalendarAsync(1L).get(1, TimeUnit.SECONDS));

        assertNotNull(calendar);
        signInCache.updateSignInCalendarAsync(1L, calendar + (1 << 5));
        Thread.sleep(10);
        int calendarAfter = StringUtil.toInteger(signInCache.getSignInCalendarAsync(1L).get(1, TimeUnit.SECONDS));
        assertEquals(calendarAfter, calendar + (1 << 5));
    }

    @Test
    public void testResignInDays() throws ExecutionException, InterruptedException, TimeoutException {
        // 补签日期
        Random random = new Random();
        int randInt = random.nextInt(28);

        conn.sync().lrem(userSignInRe(1L), 1L, String.valueOf(randInt));

        RedisFuture<List<String>> reSignInDaysBefore = signInCache.getReSignInDaysAsync(1L);
        signInCache.addResignInDaysAsync(1L, randInt);
        RedisFuture<List<String>> reSignInDaysAfter = signInCache.getReSignInDaysAsync(1L);

        List<String> before = reSignInDaysBefore.get(1, TimeUnit.SECONDS);
        List<String> after = reSignInDaysAfter.get(1, TimeUnit.SECONDS);

        after.removeAll(before);

        assertEquals(randInt, Integer.parseInt(after.get(0)));

        conn.sync().lrem(userSignInRe(1L), 1L, String.valueOf(randInt));
    }
}
