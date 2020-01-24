package pers.jiangyinzuo.carbon.dao.cache;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CreditCacheTest {
    private CreditCache creditCache;

    @Autowired
    public void setCreditCache(CreditCache creditCache) {
        this.creditCache = creditCache;
    }

    @Test
    public void testGetCredit() {
        long credit1 = creditCache.getCredit("tss");
        assertEquals(0L, credit1);
    }

    @Test
    public void testAddCredit() {
        long oldCredit = creditCache.getCredit("1");
        creditCache.addCredit("1", 3);
        long newCredit = creditCache.getCredit("1");
        assertEquals(3, newCredit - oldCredit);
    }
}
