package pers.jiangyinzuo.carbon.dao.cache;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CreditCacheTest {
    private CreditCache creditCache;

    @Autowired
    public void setCreditCache(CreditCache creditCache) {
        this.creditCache = creditCache;
    }

    @Test
    public void testGetTotalCredit() {
        var result = creditCache.getCredits(List.of(1L, 2L), "total");
        assertEquals(2, result.size());
    }
}
