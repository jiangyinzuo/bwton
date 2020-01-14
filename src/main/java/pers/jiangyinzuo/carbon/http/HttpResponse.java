package pers.jiangyinzuo.carbon.http;

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
public class HttpResponse<T> {

    @ApiModelProperty("错误码，成功时返回数字0")
    private int errCode;

    @ApiModelProperty("错误信息，成功时返回null")
    private String errMsg;

    @ApiModelProperty("请求结果")
    T result;
}
