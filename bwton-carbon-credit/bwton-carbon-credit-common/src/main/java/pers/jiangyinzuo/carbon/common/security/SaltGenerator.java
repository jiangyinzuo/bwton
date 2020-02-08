package pers.jiangyinzuo.carbon.common.security;

import java.security.SecureRandom;

/**
 * @author Jiang Yinzuo
 */
public class SaltGenerator {
    private static SecureRandom secureRandom = new SecureRandom();

    private SaltGenerator() {}

    public static byte[] getSalt32() {
        byte[] salt = new byte[32];
        secureRandom.nextBytes(salt);
        return salt;
    }
}
