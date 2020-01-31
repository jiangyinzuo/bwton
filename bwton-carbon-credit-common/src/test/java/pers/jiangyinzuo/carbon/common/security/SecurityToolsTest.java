package pers.jiangyinzuo.carbon.common.security;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class SecurityToolsTest {

    @Test
    public void testGetSalt() {
        byte[] salt1 = SaltGenerator.getSalt32();
        byte[] salt2 = SaltGenerator.getSalt32();

        assertEquals(32, salt1.length);
        assertFalse(Arrays.equals(salt1, salt2));
    }

    @Test
    public void testSha256Encrypt() {
        assertEquals(64, Sha256Util.encryptPassword("123456").length());
        assertNotEquals(Sha256Util.encryptPassword("123"), Sha256Util.encryptPassword("123"));
    }
}
