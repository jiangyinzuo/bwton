package pers.jiangyinzuo.carbon.common;

/**
 * @author Jiang Yinzuo
 */
public class StringUtil {

    private StringUtil() {}

    public static Long toLong(String number) {
        return number == null ? 0L : Long.parseLong(number);
    }

    public static Integer toInteger(String number) {
        return number == null ? 0 : Integer.parseInt(number);
    }
}
