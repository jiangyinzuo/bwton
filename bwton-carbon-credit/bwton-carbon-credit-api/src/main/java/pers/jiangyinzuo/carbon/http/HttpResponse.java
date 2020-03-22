package pers.jiangyinzuo.carbon.http;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static pers.jiangyinzuo.carbon.http.HttpResponse.HttpResponseBody.*;

/**
 * 自定义HTTP response类
 * @author Jiang Yinzuo
 */
public class HttpResponse {

    /**
     * 不带data的200状态码http response
     */
    public static final ResponseEntity<Object> OK = new ResponseEntity<>(successWithEmptyData(), HttpStatus.OK);

    /**
     * 状态码403的http response
     */
    public static final ResponseEntity<Object> FORBIDDEN = error("权限不够", HttpStatus.FORBIDDEN);

    /**
     * 状态码404的http response
     */
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

    /**
     * Http请求体
     * 请求格式：
     * {
     *     errCode: 0,
     *     errMsg: "ok",
     *     data: {...}
     * }
     * @param <T>
     */
    @Data
    static class HttpResponseBody<T> {

        /**
         * 错误码
         */
        private int errCode;

        /**
         * 错误信息
         */
        private String errMsg;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        T data;

        /**
         *
         * @param data 返回给前端的数据
         * @param <T>
         * @return http 请求体，被转为JSON后格式为：
         * {
         *     errCode: 0,
         *     errMsg: "ok",
         *     data: {...}
         * }
         */
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

        private HttpResponseBody(int errCode, String errMsg, T data) {
            this.errCode = errCode;
            this.errMsg = errMsg;
            this.data = data;
        }
    }
}
