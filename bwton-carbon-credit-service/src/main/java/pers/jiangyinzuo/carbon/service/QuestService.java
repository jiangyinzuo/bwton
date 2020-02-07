package pers.jiangyinzuo.carbon.service;

import pers.jiangyinzuo.carbon.domain.entity.QuestProgress;

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
}
