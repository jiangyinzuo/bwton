package pers.jiangyinzuo.carbon.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import pers.jiangyinzuo.carbon.common.DateUtil;
import pers.jiangyinzuo.carbon.domain.dto.PropCountDTO;

import java.time.LocalDate;
import java.util.List;

import static pers.jiangyinzuo.carbon.domain.dto.PropCountDTO.END_REWARD;
import static pers.jiangyinzuo.carbon.domain.dto.PropCountDTO.REWARD_MAP;

/**
 * 用户签到ViewObject
 *
 * @author Jiang Yinzuo
 */
public class SignInVO {

    private static final String SIGN_IN_SUCCESS = "签到成功";
    private static final String SUCCESS_RESIGN_IN = "补签成功";
    private static final String HAVE_SIGNED_IN = "已签到";
    private static final String USE_OUT_OF_RE_SIGN_CARD = "补签卡不足";
    private static final String LIMIT_USE_OF_RE_SIGN_CARD = "每月只能使用3次补签卡";

    /**
     * 签到奖励
     */
    private static final Integer[] REWARD_PERIOD = {15, 10, 7};

    /**
     * 补签日期列表
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final List<Integer> reSignIn;

    /**
     * 签到日历，二进制位1表示已签（含补签），0表示未签
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final Integer calendar;

    /**
     * 当前总连续天数
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final Integer continueDays;

    /**
     * 奖励类型: 7日签到奖励、10日签到奖励、15日签到奖励、一个月全勤奖励
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final PropCountDTO rewards;

    /**
     * 提示信息
     */
    private final String msg;

    private SignInVO(String msg) {
        this.reSignIn = null;
        this.calendar = null;
        this.continueDays = null;
        this.rewards = new PropCountDTO();
        this.msg = msg;
    }

    private SignInVO(Integer continueDays, PropCountDTO rewards, String msg) {
        this.reSignIn = null;
        this.calendar = null;
        this.continueDays = continueDays;
        this.rewards = rewards;
        this.msg = msg;
    }

    /**
     *
     * @param bitCalendar 签到日历（含今日）
     * @param continueDays 连续签到天数（含今日）
     * @param signInDate 签到日期
     * @return SignInVO
     */
    public static SignInVO signInSuccess(int bitCalendar, int continueDays, int signInDate) {
        return new SignInVO(continueDays, calcRewardsSignIn(bitCalendar, continueDays, signInDate), SIGN_IN_SUCCESS);
    }

    public static SignInVO resignInSuccess(int bitCalendar, int continueDaysBeforeReSignIn, int signInDate) {
        int continueDays;
        if (signInDate == 1) {
            continueDays = continueDaysBeforeReSignIn + 1;
        } else {
            int continueDaysOfThisMonth = 0;
            int tempBit = 1 << (signInDate - 1);
            while (tempBit > 0 && (bitCalendar & tempBit) == tempBit) {
                continueDaysOfThisMonth++;
                tempBit >>= 1;
            }
            continueDays = continueDaysBeforeReSignIn + continueDaysOfThisMonth;
        }
        return new SignInVO(continueDays, calcRewardsResignIn(bitCalendar, signInDate), SUCCESS_RESIGN_IN);
    }

    private static PropCountDTO calcRewardsResignIn(int bitCalendar, int signInDate) {
        int afterMaxRewards = getMaxContinueDays(bitCalendar);
        bitCalendar &= (1 << (signInDate) - 1);
        int beforeMaxRewards = getMaxContinueDays(bitCalendar);

        PropCountDTO propCountDTO = new PropCountDTO();

        if (beforeMaxRewards < 7) {
            if (afterMaxRewards >= 7) {
                propCountDTO.plusProps(REWARD_MAP.get(7));
            }
            if (afterMaxRewards >= 10) {
                propCountDTO.plusProps(REWARD_MAP.get(10));
            }
        } else if (beforeMaxRewards < 10) {
            if (afterMaxRewards >= 10) {
                propCountDTO.plusProps(REWARD_MAP.get(10));
            }
            if (afterMaxRewards >= 15) {
                propCountDTO.plusProps(REWARD_MAP.get(15));
            }
        } else if (beforeMaxRewards < 15 && afterMaxRewards >= 15) {
            propCountDTO.plusProps(REWARD_MAP.get(15));
        } else if (afterMaxRewards == LocalDate.now().getMonthValue()) {
            propCountDTO.plusProps(END_REWARD);
        }

        return propCountDTO;
    }

    /**
     *
     * @param bitCalendar 签到日历
     * @return 最大连续天数
     */
    private static int getMaxContinueDays(int bitCalendar) {
        int maxContinueDays = 0;
        int continueBit = 0;
        while (bitCalendar > 0) {
            if ((bitCalendar & 1) == 1) {
                continueBit++;
            } else {
                maxContinueDays = Math.max(maxContinueDays, continueBit);
            }
            bitCalendar >>= 1;
        }

        return maxContinueDays;
    }

    public static SignInVO haveSignedIn() {
        return new SignInVO(HAVE_SIGNED_IN);
    }

    /**
     * 补签卡用完了
     * @return SignInVO
     */
    public static SignInVO useOutOfResignCard() {
        return new SignInVO(USE_OUT_OF_RE_SIGN_CARD);
    }

    public static SignInVO limitUseOfResignCard() {
        return new SignInVO(LIMIT_USE_OF_RE_SIGN_CARD);
    }

    /**
     * 今日签到，根据连续签到天数计算获得的奖励
     * @param bitCalendar 用比特位表示的签到日历（若今日已签到则包含在内）
     * @param continueDays 连续签到天数（含今日签到）
     * @param signInDate 签到日期
     * @return 奖励类型
     */
    private static PropCountDTO calcRewardsSignIn(int bitCalendar, int continueDays, int signInDate) {
        PropCountDTO rewards = new PropCountDTO();
        int daysOfMonth = DateUtil.getDaysOfThisMonth();

        if (1 << (daysOfMonth - 1) == bitCalendar) {
            // 月底签到，判断有无月末大礼包
            if (signInDate == daysOfMonth) {
                rewards.plusProps(END_REWARD);
            }

            // 判断有无15日、10日、7日大礼包
            for (Integer period : REWARD_PERIOD) {
                if (continueDays % period == 0) {
                    rewards.plusProps(REWARD_MAP.get(period));
                }
                continueDays /= period;
            }
        }
        return rewards;
    }

    /**
     * 获取奖励
     * @return 奖励列表。若无奖励，返回长度为0的列表，不返回null
     */
    public PropCountDTO getRewards() {
        return rewards;
    }

    @Override
    public String toString() {
        return "SignInVO{" +
                "reSignIn=" + reSignIn +
                ", calendar=" + calendar +
                ", continueDays=" + continueDays +
                ", rewardType=" + rewards +
                ", msg='" + msg + '\'' +
                '}';
    }
}
