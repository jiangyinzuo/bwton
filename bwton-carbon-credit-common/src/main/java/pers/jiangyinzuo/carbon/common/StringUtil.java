package pers.jiangyinzuo.carbon.common;

/**
 * @author Jiang Yinzuo
 */
public class StringUtil {

    private StringUtil() {}

    public static Long toLong(String number) {
        if (number == null) {
            return 0L;
        }
        return Long.parseLong(number);
    }

    public static Integer toInteger(String number) {
        if (number == null) {
            return 0;
        }
        return Integer.parseInt(number);
    }
}
