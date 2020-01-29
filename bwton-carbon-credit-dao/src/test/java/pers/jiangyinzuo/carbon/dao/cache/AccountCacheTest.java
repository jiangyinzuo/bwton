package pers.jiangyinzuo.carbon.dao.cache;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class AccountCacheTest {

    private AccountCache accountCache;

    @Autowired
    public void setAccountCache(AccountCache accountCache) {
        this.accountCache = accountCache;
    }

    @Test
    public void testIncrementAccountTotal() {
        long total = accountCache.getAccountTotal();
        accountCache.increaseAccountTotal();
        long newTotal = accountCache.getAccountTotal();
        assertEquals(total + 1, newTotal);
    }
}
