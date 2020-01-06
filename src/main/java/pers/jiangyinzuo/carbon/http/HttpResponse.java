package pers.jiangyinzuo.carbon.http;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jiang Yinzuo
 *
 * Common http response.
 */
public class HttpResponse extends ResponseEntity<Map<String, Object>> {

    private HttpResponse(Map<String, Object> body, HttpStatus status) {
        super(body, status);
    }

    private static Map<String, Object> initBody(int errCode, String errMsg) {
        Map<String, Object> body = new HashMap<>(10);
        body.put("errcode", errCode);
        body.put("errmsg", errMsg);
        return body;
    }

    /**
     * Success without result data, used for updating data
     * @return http response
     */
    public static HttpResponse success() {
        return new HttpResponse(initBody(0, null), HttpStatus.OK);
    }

    /**
     * Success with result data, used for retrieving data.
     * @param data result data
     * @return http response
     */
    public static HttpResponse success(Object data) {
        Map<String, Object> body = initBody(0, null);
        body.put("result", data);
        return new HttpResponse(body, HttpStatus.OK);
    }

    /**
     * Respond when something is wrong.
     * @param e global custom exception
     * @return http response
     */
    public static HttpResponse fail(CustomHttpException e) {
        return new HttpResponse(initBody(-1, e.getErrMsg()), e.getStatusCode());
    }
}
