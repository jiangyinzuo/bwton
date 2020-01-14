package pers.jiangyinzuo.carbon.http;

import org.springframework.http.HttpStatus;

/**
 * @author Jiang Yinzuo
 */
public class CustomHttpException extends RuntimeException {

    private final HttpStatus statusCode;
    private final String errMsg;

    public CustomHttpException(HttpStatus statusCode, String errMsg) {
        this.statusCode = statusCode;
        this.errMsg = errMsg;
    }

    public HttpStatus getStatusCode() {
        return this.statusCode;
    }

    public String getErrMsg() {
        return errMsg;
    }
}
