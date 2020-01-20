package pers.jiangyinzuo.carbon.http;

import com.fasterxml.jackson.core.JsonParseException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


/**
 * @author Jiang Yinzuo
 */
@ControllerAdvice
public class HttpExceptionHandler {

    @ExceptionHandler(CustomHttpException.class)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> handleCustomHttpException(CustomHttpException e) {
        return new ResponseEntity<>(createBody(e.getErrMsg()), e.getStatusCode());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(createBody(Objects.requireNonNull(e.getBindingResult().getFieldError()).getDefaultMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({HttpMessageNotReadableException.class, JsonParseException.class})
    @ResponseBody
    public ResponseEntity<Map<String, Object>> handleJsonParseException(Exception e) {
        return new ResponseEntity<>(createBody("JSON解析异常"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<Map<String, Object>> handleUnexpectedException(Exception e) {
        return new ResponseEntity<>(createBody("未知错误"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private static Map<String, Object> createBody(String errMsg) {
        Map<String, Object> body = new HashMap<>(10);
        body.put("errCode", -1);
        body.put("errMsg", errMsg);
        return body;
    }
}
