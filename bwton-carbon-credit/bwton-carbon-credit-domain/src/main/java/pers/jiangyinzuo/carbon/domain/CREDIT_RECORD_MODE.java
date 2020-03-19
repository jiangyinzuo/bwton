package pers.jiangyinzuo.carbon.domain;

/**
 * @author Jiang Yinzuo
 */

public enum CREDIT_RECORD_MODE {
    /**
     * 碳积分的3种记录模式：总量、每周累计、剩余总量
     */
    TOTAL("total"), WEEK("week"), REMAIN("remain");

    private String mode;
    CREDIT_RECORD_MODE(String mode) {
        this.mode = mode;
    }

    public static boolean contains(String mode) {
        return TOTAL.getMode().equals(mode) || WEEK.getMode().equals(mode) || REMAIN.getMode().equals(mode);
    }

    public String getMode() {
        return mode;
    }
}
