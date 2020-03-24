package pers.jiangyinzuo.carbon.service;

import pers.jiangyinzuo.carbon.domain.entity.QuestProgress;
import pers.jiangyinzuo.carbon.domain.vo.SignInVO;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;

/**
 * @author Jiang Yinzuo
 */
public interface QuestService {

    /**
     * 获取任务进度
     * @param userId 用户ID
     * @return 任务进度实体类
     */
    QuestProgress getQuestProgress(Long userId);

    /**
     * 用户签到
     * @param userId 用户ID
     * @param signInDate 签到日期，范围是0-31，其中0表示当天签到
     * @return 用户签到展示数据对象
     */
    SignInVO signIn(Long userId, int signInDate) throws InterruptedException, TimeoutException, ExecutionException;
}
