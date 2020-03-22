package pers.jiangyinzuo.carbon.domain.vo;

import lombok.Data;

import java.util.List;

/**
 * 用户签到ViewObject
 * @author Jiang Yinzuo
 */
@Data
public class SignInVO {
    /**
     * 补签日期列表
     */
    List<Integer> reSignIn;

    /**
     * 签到日历，二进制位1表示已签（含补签），0表示未签
     */
    Integer calendar;

    /**
     * 本月之前连续签到天数
     */
    Integer lastContinueDays;

    /**
     * 当前总连续天数
     */
    Integer continueDays;

    /**
     * 奖励类型: 7日签到奖励、10日签到奖励、15日签到奖励、一个月全勤奖励
     */
    Integer rewardType;
}
