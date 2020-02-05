package pers.jiangyinzuo.carbon.dao.cache;

/**
 * @author Jiang Yinzuo
 */
public class KeyBuilder {
    private static final String BWTON_USER = "bt:user:";
    private static final String CREDIT = ":credit:";
    private static final String RECORD = ":record:";

    private KeyBuilder() {}

    public static String userCredit(Long userId, String span) {
        return BWTON_USER + userId + CREDIT + span;
    }

    public static String userCreditRecord(Long userId, int dayOfWeek) {
        return BWTON_USER + userId + CREDIT + RECORD + dayOfWeek;
    }

    public static String userSignatureKey(String userId) {
        return BWTON_USER + userId + ":sk";
    }
}
