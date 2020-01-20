package pers.jiangyinzuo.carbon.common;

import org.junit.jupiter.api.BeforeAll;

import org.junit.Assert;

public class Sha256UtilTest {

    private static Long userId;

    @BeforeAll
    static void initAll() {
        userId = 12345L;
    }

    public void testEncrypt() {
        Sha256Util.encrypt(12345L);
    }
}
