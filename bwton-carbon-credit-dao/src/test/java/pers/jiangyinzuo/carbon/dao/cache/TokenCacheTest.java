package pers.jiangyinzuo.carbon.dao.cache;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import pers.jiangyinzuo.carbon.common.security.Sha256Util;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TokenCacheTest {

    @Autowired
    private TokenCache tokenCache;

    @Test
    public void testSetSignature() {
        tokenCache.setSignature("1", Sha256Util.genSignature(Sha256Util.genCredential()));
        String signature = tokenCache.getSignature("1");
        assertEquals(64, signature.length());
    }
}
