package pers.jiangyinzuo.carbon.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import pers.jiangyinzuo.carbon.domain.validation.annotation.DropValue;
import pers.jiangyinzuo.carbon.domain.validation.annotation.ID;

/**
 * @author Jiang Yinzuo
 */
@Data
public class DropPickingDTO {

    /**
     * 每个积分小水滴的过期时间
     */
    public static final long EXPIRE_SPAN = 24 * 3600 * 1000L;

    @ID
    private Long pickerUserId;

    @ID
    private Long pickedUserId;

    @DropValue
    private String dropValue;

    @JsonIgnore
    private Long matureTime;

    @JsonIgnore
    private Integer value;

    public DropPickingDTO(Long pickerUserId, Long pickedUserId, String dropValue) {
        this.pickerUserId = pickerUserId;
        this.pickedUserId = pickedUserId;
        this.dropValue = dropValue;
        String[] values = dropValue.split(".", 2);
        matureTime = Long.parseLong(values[0]);
        value = Integer.parseInt(values[1]);
    }

    /**
     * 判断小水滴是否已过期
     * @return 已过期返回true; 未过期返回false
     */
    public boolean isOutOfDate() {
        return matureTime + EXPIRE_SPAN < System.currentTimeMillis();
    }

    /**
     * 判断是不是帮自己摘小水滴
     * @return 是: true; 否: false
     */
    public boolean isSelf() {
        return pickedUserId.equals(pickerUserId);
    }
}
