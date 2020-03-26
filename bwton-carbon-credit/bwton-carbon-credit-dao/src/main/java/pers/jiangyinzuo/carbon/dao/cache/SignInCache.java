package pers.jiangyinzuo.carbon.dao.cache;

import io.lettuce.core.RedisFuture;

import java.util.List;

/**
 * 签到
 * @author Jiang Yinzuo
 */
public interface SignInCache {

    /**
     * 异步获取用户签到日历表
     * @param userId 用户ID
     * @return 二进制位1表示签到，0表示未签
     */
    RedisFuture<String> getSignInCalendarAsync(Long userId);

    /**
     * 用户在签到日历上增加签到日期
     * @param userId 用户ID
     * @param newBitCalendar 新的签到日期
     */
    void updateSignInCalendarAsync(Long userId, int newBitCalendar);

    /**
     * 获取补签日期
     * @param userId 用户ID
     * @return 补签日期列表
     */
    RedisFuture<List<String>> getReSignInDaysAsync(Long userId);

    /**
     * 添加补签日期
     * @param userId 用户ID
     * @param resignInDay 补签日期
     */
    void addResignInDaysAsync(Long userId, long resignInDay);

    /**
     * 获取用户连续登录天数
     * @param userId 用户ID
     * @return 连续登录天数
     */
    RedisFuture<String> getContinueSignInDaysAsync(Long userId);

    /**
     * 获取用户本月之前的连续登录天数
     * @param userId 用户ID
     * @return 本月之前的连续登录天数
     */
    RedisFuture<String> getLastContinueSignInDaysAsync(Long userId);

    /**
     * 增加用户连续登录天数
     * @param userId 用户ID
     * @param days 连续登录天数
     */
    void addContinueDaysAsync(Long userId, long days);

    /**
     * 异步更新上个月之前的连续签到天数
     * @param userId 用户ID
     * @param newLastContinueDays 更新的值
     */
    void updateLastContinueDaysAsync(Long userId, int newLastContinueDays);
}
