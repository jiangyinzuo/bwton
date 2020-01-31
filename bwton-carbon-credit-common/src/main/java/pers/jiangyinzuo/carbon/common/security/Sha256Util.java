package pers.jiangyinzuo.carbon.common.security;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.codec.binary.Hex;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.util.UUID;

/**
 * @author Jiang Yinzuo
 */
@Log4j2
public class Sha256Util {

    private Sha256Util() {}
    
    public static String genCredential() {
        byte[] credentialBytes = DigestUtils.getSha256Digest().digest(UUID.randomUUID().toString().getBytes());
        return Hex.encodeHexString(credentialBytes);
    }

    public static String encryptPassword(String password) {
        return encryptPassword(password, SaltGenerator.getSalt32());
    }

    public static String encryptPassword(String password, byte[] salt) {
        byte[] cipherBytes = DigestUtils.getSha256Digest().digest(ArrayUtils.addAll(password.getBytes(), salt));
        return Hex.encodeHexString(cipherBytes);
    }

    public static String genSignature(String credential) {
        if (credential == null || credential.isBlank()) {
            return "";
        }
        byte[] cipherBytes = DigestUtils.getSha256Digest().digest(credential.getBytes());
        return Hex.encodeHexString(cipherBytes);
    }
}
