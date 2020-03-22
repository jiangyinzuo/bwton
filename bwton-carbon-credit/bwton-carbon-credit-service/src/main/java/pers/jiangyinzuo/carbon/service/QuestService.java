package pers.jiangyinzuo.carbon.service;

import pers.jiangyinzuo.carbon.domain.entity.QuestProgress;
import pers.jiangyinzuo.carbon.domain.vo.SignInVO;

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
     * @return 连续签到天数
     */
    SignInVO signIn(Long userId);
}
