package pers.jiangyinzuo.carbon.http;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @author Jiang Yinzuo
 *
 * Common Ajax response.
 */
@Data
public class AjaxResponse {
    private int statusCode;
    @JsonProperty("errcode")
    private int errCode;
    @JsonProperty("errmsg")
    private String errMsg;
    @JsonProperty("result")
    private Object data = null;

    private AjaxResponse() {}

    public static AjaxResponse success() {
        AjaxResponse response = new AjaxResponse();
        response.setErrCode(0);
        response.setErrMsg(null);
        response.setStatusCode(200);
        return response;
    }

    public static AjaxResponse success(Object data) {
        AjaxResponse response = success();
        response.setData(data);
        return response;
    }
}
