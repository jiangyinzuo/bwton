package pers.jiangyinzuo.carbon.common;

import java.util.TimeZone;

/**
 * 时间相关的工具类
 *
 * @author Jiang Yinzuo
 */
public class TimeUtil {

    private static final long ONE_DAY = 24L * 3600 * 1000;
    private static final long RAW_OFFSET = TimeZone.getDefault().getRawOffset();

    private TimeUtil() {}

    /**
     * 获取当地时间今日零点的时间戳
     * @return Unix时间戳
     */
    public static long getTodayMidnightTimestamp() {
        long currentTime = System.currentTimeMillis();
        return currentTime - currentTime % ONE_DAY - RAW_OFFSET;
    }

    /**
     * 获取当地时间本周周日凌晨0点的时间戳
     * @return Unix时间戳
     */
    public static long getBeginOfThisWeekTimestamp() {
        long currentTime = System.currentTimeMillis();
        return currentTime - currentTime % (ONE_DAY * 7) - RAW_OFFSET;
    }
}
