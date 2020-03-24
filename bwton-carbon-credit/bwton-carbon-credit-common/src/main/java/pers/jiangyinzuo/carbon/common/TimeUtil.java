package pers.jiangyinzuo.carbon.common;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.TimeZone;

/**
 * 基于Java8API的时间相关的工具类
 *
 * @author Jiang Yinzuo
 * @see LocalDate
 */
public class TimeUtil {

    private static final ZoneOffset OFFSET = ZoneOffset.of("+8");
    private static final long ONE_DAY = 24L * 3600 * 1000;
    private static final long RAW_OFFSET = TimeZone.getDefault().getRawOffset();

    private TimeUtil() {
    }

    /**
     * 获取当地时间今日零点的时间戳
     *
     * @return Unix时间戳
     */
    public static long getTodayMidnightTimestamp() {
        long currentTime = System.currentTimeMillis();
        return currentTime - currentTime % ONE_DAY - RAW_OFFSET;
    }

    /**
     * 获取当地时间本周周日凌晨0点的时间戳
     *
     * @return Unix时间戳
     */
    public static long getBeginOfThisWeekTimestamp() {
        long currentTime = System.currentTimeMillis();
        return currentTime - currentTime % (ONE_DAY * 7) - RAW_OFFSET;
    }

    /**
     * 获取下个月凌晨0点的时间戳
     *
     * @return Unix时间戳
     */
    public static long getBeginOfNextMonthTimestamp() {
        return LocalDate.now().atStartOfDay().withDayOfMonth(1).plusMonths(1).toInstant(OFFSET).toEpochMilli();
    }
}
