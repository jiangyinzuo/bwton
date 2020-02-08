package pers.jiangyinzuo.carbon.dao.cache;

import pers.jiangyinzuo.carbon.domain.entity.QuestProgress;

/**
 * @author Jiang Yinzuo
 */
public interface QuestCache {

    /**
     * 获取任务进度
     * @param userId 用户ID
     * @return 任务进度实体类
     */
    QuestProgress getQuestProgress(Long userId);
}
