package pers.jiangyinzuo.carbon.common;

import org.apache.commons.codec.binary.Hex;
import org.springframework.http.HttpStatus;
import pers.jiangyinzuo.carbon.http.CustomHttpException;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Jiang Yinzuo
 */
public class Sha256Util {
    private static MessageDigest messageDigest;

    static {
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new CustomHttpException(HttpStatus.INTERNAL_SERVER_ERROR, "no such algorithm");
        }
    }

    public static String encrypt(Long userId) {
        byte[] cipherBytes = messageDigest.digest(userId.toString().getBytes());
        return Hex.encodeHexString(cipherBytes);
    }
}
