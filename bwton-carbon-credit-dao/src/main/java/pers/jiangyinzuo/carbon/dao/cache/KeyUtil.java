package pers.jiangyinzuo.carbon.dao.cache;

/**
 * @author Jiang Yinzuo
 */
public class KeyUtil {
    public static final int FRIENDS_EXPIRE_TIME = 259200;

    private KeyUtil() {}

    public static String genUserFriKey(Long userId) {
        return "bt:user:" + userId + ":fri";
    }

    public static byte[] genUserFriKeyBytes(Long userId) {
        return genUserFriKey(userId).getBytes();
    }

    public static String gentUserInfoKey(Long userId) {
        return "bt:user:" + userId + ":info";
    }

    public static byte[] genUserInfoKeyBytes(Long userId) {
        return gentUserInfoKey(userId).getBytes();
    }
}
