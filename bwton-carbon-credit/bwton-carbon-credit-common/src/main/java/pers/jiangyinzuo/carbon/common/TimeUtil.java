package pers.jiangyinzuo.carbon.common;

import java.util.TimeZone;

/**
 * @author Jiang Yinzuo
 */
public class TimeUtil {

    private static final long ONE_DAY = 24L * 3600 * 1000;
    private static final long RAW_OFFSET = TimeZone.getDefault().getRawOffset();

    private TimeUtil() {}

    public static long getTodayTimestamp() {
        long cur = System.currentTimeMillis();
        return cur - cur % ONE_DAY - RAW_OFFSET;
    }
}
