package pers.jiangyinzuo.carbon.dao.cache;

/**
 * @author Jiang Yinzuo
 */
public class KeyBuilder {
    public static final int FRIENDS_EXPIRE_TIME = 259200;
    public static final String USER_TOTAL = "bt:user:total";

    private KeyBuilder() {}

    public static String userFri(Long userId) {
        return "bt:user:" + userId + ":fri";
    }

    public static byte[] userFriBytes(Long userId) {
        return userFri(userId).getBytes();
    }

    public static String userInfo(Long userId) {
        return "bt:user:" + userId + ":info";
    }

    public static byte[] userInfoBytes(Long userId) {
        return userInfo(userId).getBytes();
    }

    public static byte[] userCreditTodayBytes(Long userId) {
        return ("bt:user:" + userId + ":credit:today").getBytes();
    }

    public static byte[] userCreditHistoryTotalBytes(Long userId) {
        return ("bt:user:" + userId + ":credit:history:total").getBytes();
    }
}
