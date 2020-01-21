package pers.jiangyinzuo.carbon.http;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Jiang Yinzuo
 *
 * Common http response.
 */
@Data
@AllArgsConstructor
@ApiModel("HTTP Response")
public class HttpResponseBody<T> {

    @ApiModelProperty("错误码，成功时返回数字0")
    private int errCode;

    @ApiModelProperty("错误信息，成功时返回'ok'")
    private String errMsg;

    @ApiModelProperty("请求结果")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    T data;
}
