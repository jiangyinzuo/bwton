package pers.jiangyinzuo.carbon.common;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期相关工具类
 * @author Jiang Yinzuo
 */
public class DateUtil {

    /**
     * 获取当前时间的年月日
     * @return ”yyyy-MM-dd"格式的年月日字符串
     */
    public static String getCurrentYearMonthDay() {
        return getCurrentFormatDate("yyyy-MM-dd");
    }

    /**
     * 获取当前时间的格式化的日期
     * @param fmt 格式化字符串, 如"yy-MM-dd"
     * @return 格式化的日期字符串
     */
    public static String getCurrentFormatDate(String fmt) {
        return new SimpleDateFormat(fmt).format(new Date());
    }

    private DateUtil() {}
}
