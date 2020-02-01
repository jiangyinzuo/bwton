package pers.jiangyinzuo.carbon.dao.cache;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pers.jiangyinzuo.carbon.common.security.EncryptUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TokenCacheTest {

    @Autowired
    private TokenCache tokenCache;

    @Test
    public void testSetSignature() {
        tokenCache.setSignature("1", EncryptUtil.genSignature(EncryptUtil.genCredential()));
        String signature = tokenCache.getSignature("1");
        assertEquals(64, signature.length());
    }
}
