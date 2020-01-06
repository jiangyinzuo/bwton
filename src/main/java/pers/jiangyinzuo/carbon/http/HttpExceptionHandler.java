package pers.jiangyinzuo.carbon.http;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author Jiang Yinzuo
 */
@ControllerAdvice
public class HttpExceptionHandler {

    @ExceptionHandler(CustomHttpException.class)
    @ResponseBody
    public HttpResponse handleCustomHttpException(CustomHttpException e) {
        return HttpResponse.fail(e);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public HttpResponse handleUnexpectedException(Exception e) {
        return HttpResponse.fail(
                new CustomHttpException(HttpStatus.INTERNAL_SERVER_ERROR,
                        "未知错误"));
    }
}
