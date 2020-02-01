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

    public static String[] multiUserFri(Long ...userIds) {
        String[] result = new String[userIds.length];
        int i = 0;
        for (Long id : userIds) {
            result[i++] = userFri(id);
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

    public static String userCreditToday(Long userId) {
        return BWTON_USER + userId + ":credit:today";
    }

    public static String userCreditHistoryTotal(Long userId) {
        return BWTON_USER + userId + ":credit:history:total";
    }

    public static String userSignatureKey(String userId) {
        return BWTON_USER + userId + ":sk";
    }
}
