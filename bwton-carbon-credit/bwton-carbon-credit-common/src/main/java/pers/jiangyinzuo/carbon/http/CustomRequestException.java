package pers.jiangyinzuo.carbon.http;

import org.springframework.http.HttpStatus;

/**
 * @author Jiang Yinzuo
 */
public class CustomRequestException extends Exception {

    private final HttpStatus statusCode;
    private final String errMsg;
    private final int errCode;

    public CustomRequestException(HttpStatus statusCode, String errMsg, int errCode) {
        this.statusCode = statusCode;
        this.errMsg = errMsg;
        this.errCode = errCode;
    }

    public HttpStatus getStatusCode() {
        return this.statusCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public int getErrCode() {
        return errCode;
    }
}
