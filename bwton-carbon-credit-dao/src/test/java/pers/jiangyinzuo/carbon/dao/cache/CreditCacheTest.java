package pers.jiangyinzuo.carbon.dao.cache;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class CreditCacheTest {
    private CreditCache creditCache;

    @Autowired
    public void setCreditCache(CreditCache creditCache) {
        this.creditCache = creditCache;
    }

    @Test
    public void testGetTotalCredit() {
        var result = creditCache.getTotalCredits(List.of(1L, 2L));
        System.out.println(result);
    }
}