package pers.jiangyinzuo.carbon.http;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static pers.jiangyinzuo.carbon.http.HttpResponse.HttpResponseBody.*;

/**
 * @author Jiang Yinzuo
 */
public class HttpResponse {

    public static final ResponseEntity<Object> OK = new ResponseEntity<>(successWithEmptyData(), HttpStatus.OK);
    public static final ResponseEntity<Object> FORBIDDEN = error("权限不够", HttpStatus.FORBIDDEN);
    public static final ResponseEntity<Object> NOT_FOUND = ResponseEntity.notFound().build();

    private HttpResponse() {
    }

    public static ResponseEntity<Object> ok(int errCode, String errMsg) {
        return new ResponseEntity<>(failWithEmptyData(errCode, errMsg), HttpStatus.OK);
    }

    public static ResponseEntity<Object> ok(Object data) {
        return new ResponseEntity<>(success(data), HttpStatus.OK);
    }

    public static ResponseEntity<Object> ok(Object data, int errCode, String errMsg) {
        return new ResponseEntity<>(fail(errCode, errMsg, data), HttpStatus.OK);
    }

    public static ResponseEntity<Object> badRequest(String errMsg) {
        return error(errMsg, HttpStatus.BAD_REQUEST);
    }

    private static ResponseEntity<Object> error(int errCode, String errMsg, HttpStatus statusCode) {
        return new ResponseEntity<>(failWithEmptyData(errCode, errMsg), statusCode);
    }

    private static ResponseEntity<Object> error(String errMsg, HttpStatus statusCode) {
        return error(-1, errMsg, statusCode);
    }

    @Data
    @AllArgsConstructor
    static class HttpResponseBody<T> {

        private int errCode;

        private String errMsg;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        T data;

        public static <T> HttpResponseBody<T> success(T data) {
            return new HttpResponseBody<>(0, "ok", data);
        }

        public static HttpResponseBody<Void> successWithEmptyData() {
            return new HttpResponseBody<>(0, "ok", null);
        }

        public static <T> HttpResponseBody<T> fail(int errCode, String errMsg, T data) {
            return new HttpResponseBody<>(errCode, errMsg, data);
        }

        public static HttpResponseBody<Void> failWithEmptyData(int errCode, String errMsg) {
            return new HttpResponseBody<>(errCode, errMsg, null);
        }
    }
}
