package pers.jiangyinzuo.carbon.common.security;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.ArrayUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Jiang Yinzuo
 */
@Log4j2
public class Sha256Util {

    private static MessageDigest messageDigest;

    static {
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            log.error("no algorithm SHA-256", e);
        }
    }

    private Sha256Util() {}

    public static String genToken(String password) {
        byte[] cipherBytes = messageDigest.digest(
                ArrayUtils.addAll(SaltGenerator.getSalt32(), password.getBytes())
        );
        return Hex.encodeHexString(cipherBytes);
    }

    public static String encrypt(String password) {
        return encrypt(password, SaltGenerator.getSalt32());
    }

    public static String encrypt(String password, byte[] salt) {
        byte[] cipherBytes = messageDigest.digest(ArrayUtils.addAll(password.getBytes(), salt));
        return Hex.encodeHexString(cipherBytes);
    }
}
