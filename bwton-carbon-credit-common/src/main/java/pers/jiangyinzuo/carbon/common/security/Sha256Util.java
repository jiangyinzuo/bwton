package pers.jiangyinzuo.carbon.common.security;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.lang3.ArrayUtils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.UUID;

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

    public static String genCredential() {
        byte[] credentialBytes = messageDigest.digest(UUID.randomUUID().toString().getBytes());
        return Hex.encodeHexString(credentialBytes);
    }

    public static String encryptPassword(String password) {
        return encryptPassword(password, SaltGenerator.getSalt32());
    }

    public static String encryptPassword(String password, byte[] salt) {
        byte[] cipherBytes = messageDigest.digest(ArrayUtils.addAll(password.getBytes(), salt));
        return Hex.encodeHexString(cipherBytes);
    }

    public static String genSignature(String credential) {
        if (credential == null || credential.isBlank()) {
            return "";
        }
        byte[] cipherBytes = messageDigest.digest(credential.getBytes());
        return Hex.encodeHexString(cipherBytes);
    }
    
}
