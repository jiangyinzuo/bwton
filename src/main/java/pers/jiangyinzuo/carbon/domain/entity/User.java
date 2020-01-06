package pers.jiangyinzuo.carbon.domain.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

/**
 * @author Jiang Yinzuo
 */
@Data
@Builder
public class User {

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("user_name")
    private String userName;

    private Integer sex;

    @JsonProperty("mobile_phone")
    private String mobilePhone;

    private String profession;

    @JsonProperty("id_no")
    private String idNo;

    @JsonProperty("id_type")
    private Integer idType;

    @JsonProperty("user_image_path")
    private String userImagePath;

    private String nickname;

    @JsonProperty("city_id")
    private String cityId;

    @JsonProperty("real_name_reg")
    private Integer realNameReg;

    @JsonProperty("real_name_auth")
    private Integer realNameAuth;

    @JsonProperty("real_name_chk")
    private Integer realNameChk;

    @JsonProperty("real_name_open")
    private Integer realNameOpen;
}
