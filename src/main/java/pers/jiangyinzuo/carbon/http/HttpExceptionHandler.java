package pers.jiangyinzuo.carbon.http;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;


/**
 * @author Jiang Yinzuo
 */
@ControllerAdvice
public class HttpExceptionHandler {

    @ExceptionHandler(CustomHttpException.class)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> handleCustomHttpException(CustomHttpException e) {
        return new ResponseEntity<>(initBody(e.getErrMsg()), e.getStatusCode());
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> handleUnexpectedException(Exception e) {
        return new ResponseEntity<>(initBody("未知错误"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private static Map<String, Object> initBody(String errMsg) {
        Map<String, Object> body = new HashMap<>(10);
        body.put("errcode", -1);
        body.put("errmsg", errMsg);
        return body;
    }
}
