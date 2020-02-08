package pers.jiangyinzuo.carbon.util;


import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author Jiang Yinzuo
 */
public class HttpHeaderUtil {

    private HttpHeaderUtil() {}

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

    public static Long getUserId(String rawAuthToken) {
        if (rawAuthToken != null && rawAuthToken.startsWith(AUTHORIZATION_PREFIX)) {
            try {
                String credential = new String(Base64.getUrlDecoder().decode(rawAuthToken.substring(7)), StandardCharsets.UTF_8);
                return Long.parseLong(credential.split(":", 2)[0]);
            } catch (Exception e) {
                return -1L;
            }
        } else {
            return -1L;
        }
    }
}
