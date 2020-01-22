package pers.jiangyinzuo.carbon.dao.cache;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
public class TokenCacheTest {
    @MockBean
    private TokenCache tokenCache;

    @Test
    public void testSetToken() {
        assertNotNull(tokenCache);
    }
}
