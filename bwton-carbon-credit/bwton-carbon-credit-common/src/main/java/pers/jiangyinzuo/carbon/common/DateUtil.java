package pers.jiangyinzuo.carbon.common;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 基于Java8API的日期相关工具类
 * @author Jiang Yinzuo
 *
 * @see LocalDate
 */
public class DateUtil {

    private static final DateTimeFormatter DEFAULT_DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * 获取当前时间的年月日
     * @return ”yyyy-MM-dd"格式的年月日字符串
     */
    public static String getDefaultLocalDateNow() {
        return LocalDate.now().format(DEFAULT_DATE_TIME_FORMATTER);
    }

    private DateUtil() {}

    /**
     * 获取本月共几天
     * @return 本月共几天，range from 1 to 31
     */
    public static int getDaysOfThisMonth() {
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        if (year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
            return today.getMonth().maxLength();
        }
        return today.getMonth().minLength();
    }
}
