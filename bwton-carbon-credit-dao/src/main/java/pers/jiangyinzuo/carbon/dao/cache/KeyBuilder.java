package pers.jiangyinzuo.carbon.dao.cache;

/**
 * @author Jiang Yinzuo
 */
public class KeyBuilder {
    public static final int FRIENDS_EXPIRE_TIME = 259200;
    private static final String BWTON_USER = "bt:user:";

    private KeyBuilder() {}

    public static String userFri(Long userId) {
        return BWTON_USER + userId + ":fri";
    }

    public static byte[][] multiUserFriBytes(Long ...userIds) {
        byte[][] result = new byte[userIds.length][];
        int i = 0;
        for (Long id : userIds) {
            result[i++] = userFriBytes(id);
        }
        return result;
    }

    public static byte[] userFriBytes(Long userId) {
        return userFri(userId).getBytes();
    }

    public static String userInfo(Long userId) {
        return BWTON_USER + userId + ":info";
    }

    public static byte[] userInfoBytes(Long userId) {
        return userInfo(userId).getBytes();
    }

    public static byte[] userCreditTodayBytes(Long userId) {
        return (BWTON_USER + userId + ":credit:today").getBytes();
    }

    public static byte[] userCreditHistoryTotalBytes(Long userId) {
        return (BWTON_USER + userId + ":credit:history:total").getBytes();
    }

    public static String userSignatureKey(String userId) {
        return BWTON_USER + userId + ":sk";
    }
}
