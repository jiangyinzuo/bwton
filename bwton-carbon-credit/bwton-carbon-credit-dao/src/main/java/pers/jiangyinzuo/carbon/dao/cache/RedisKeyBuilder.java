package pers.jiangyinzuo.carbon.dao.cache;

/**
 * Redis所有的键
 *
 * @author Jiang Yinzuo
 */
public class RedisKeyBuilder {
    private static final String BWTON_USER = "bt:user:";
    private static final String CREDIT = ":credit:";
    private static final String RECORD = "record";
    private static final String SIGN_IN = ":signin:";

    private RedisKeyBuilder() {
    }

    public static String userCredit(Long userId, String span) {
        return BWTON_USER + userId + CREDIT + span;
    }

    /**
     * 用户被采摘碳积分小水滴记录的key
     *
     * @param collectedUserId 被采摘的用户ID
     * @return key
     */
    public static String creditDropPickedRecord(Long collectedUserId) {
        return BWTON_USER + collectedUserId + CREDIT + RECORD;
    }

    public static String userCreditDrops(Long userId) {
        return BWTON_USER + userId + CREDIT + "drops";
    }

    public static String userQuestProgress(Long userId) {
        return BWTON_USER + userId + ":quest:progress";
    }

    public static String userSignatureKey(String userId) {
        return BWTON_USER + userId + ":sk";
    }

    public static String userSignInCalendar(Long userId) {
        return BWTON_USER + userId + SIGN_IN + "calendar";
    }

    public static String userSignInRe(Long userId) {
        return BWTON_USER + userId + SIGN_IN + "re";
    }

    public static String userSignInContinue(Long userId) {
        return BWTON_USER + userId + SIGN_IN + "continue";
    }

    public static String userSignInLast(Long userId) {
        return BWTON_USER + userId + SIGN_IN + "last";
    }

    public static String userCreditIsCovered(Long userId) {
        return BWTON_USER + userId + CREDIT + "cv";
    }
}
