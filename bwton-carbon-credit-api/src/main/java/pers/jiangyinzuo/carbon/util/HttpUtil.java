package pers.jiangyinzuo.carbon.util;


import javax.servlet.http.HttpServletRequest;

/**
 * @author Jiang Yinzuo
 */
public class HttpUtil {

    private HttpUtil() {}

    private static final String AUTHORIZATION_PREFIX = "Bearer ";

    public static String getAuthBase64Token(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        String authHeader = request.getHeader("Authorization");
        if (authHeader != null && authHeader.startsWith(AUTHORIZATION_PREFIX)) {
            return authHeader.substring(7);
        } else {
            return null;
        }
    }
}
