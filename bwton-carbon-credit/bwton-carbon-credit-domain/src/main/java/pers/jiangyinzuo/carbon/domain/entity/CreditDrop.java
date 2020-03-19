package pers.jiangyinzuo.carbon.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import pers.jiangyinzuo.carbon.domain.validation.annotation.DropValue;
import pers.jiangyinzuo.carbon.domain.validation.annotation.ID;

/**
 * @author Jiang Yinzuo
 */
@Data
public class CreditDrop {

    /**
     * 每个积分小水滴成熟到过期的时间间隔
     */
    public static final long EXPIRE_SPAN = 24 * 3600 * 1000L;
    /**
     * 积分小水滴成熟天数
     */
    public static final Long MATURE_SPAN_MILLIS = 24 * 3600 * 1000L;
    /**
     * 一个用户最多拥有的积分小水滴数量
     */
    public static final long MAXIMUM_CREDIT_DROP_COUNT = 10;

    /**
     * 采摘积分小水滴的用户ID
     */
    @ID
    private Long pickerUserId;

    /**
     * 被采摘积分小水滴的用户ID
     */
    @ID
    private Long pickedUserId;

    /**
     * 获得积分小水滴的用户ID
     */
    @ID
    private Long gainerUserId;

    @DropValue
    private String dropValue;

    @JsonIgnore
    private Long matureTime;

    @JsonIgnore
    private Integer value;

    public CreditDrop(Long pickerUserId, Long pickedUserId, String dropValue) {
        this.pickerUserId = pickerUserId;
        this.pickedUserId = pickedUserId;
        this.dropValue = dropValue;
        setValues();
    }

    public void setDropValue(String dropValue) {
        this.dropValue = dropValue;
        setValues();
    }

    private void setValues() {
        String[] values = dropValue.split(".", 2);
        matureTime = Long.parseLong(values[0]);
        value = Integer.parseInt(values[1]);
    }

    public String getValueStr() {
        return dropValue.split(".", 2)[1];
    }

    /**
     * 判断小水滴是否已过期
     *
     * @return 已过期返回true; 未过期返回false
     */
    public boolean isOutOfDate() {
        return matureTime + EXPIRE_SPAN < System.currentTimeMillis();
    }

    /**
     * 判断是不是自己摘小水滴
     *
     * @return 是: true
     */
    public boolean isSelf() {
        return pickedUserId.equals(pickerUserId);
    }

    /**
     * 判断是否被盗
     *
     * @return 是：true
     */
    public boolean isStolen() {
        return pickerUserId.equals(gainerUserId) && !isSelf();
    }

    /**
     * 存到Redis中的采摘记录
     * @return 字符串
     */
    public String pickRecord() {
        return pickerUserId + (isStolen() ? "-" : "+") + value;
    }
}
