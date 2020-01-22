package pers.jiangyinzuo.carbon.dao.cache;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class TokenCacheTest {

    @Autowired
    private TokenCache tokenCache;

    @Test
    public void testSetToken() {
        tokenCache.setToken(1L, "bcd");
        assertTrue(tokenCache.validateToken(1L, "bcd"));
    }
}
