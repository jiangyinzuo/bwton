package pers.jiangyinzuo.carbon.common.http;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Jiang Yinzuo
 *
 * Common http response.
 */
@Data
@AllArgsConstructor
public class HttpResponseBody<T> {

    private int errCode;

    private String errMsg;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    T data;
}
