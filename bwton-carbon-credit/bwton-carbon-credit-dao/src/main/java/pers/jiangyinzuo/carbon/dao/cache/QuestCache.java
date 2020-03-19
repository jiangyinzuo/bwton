package pers.jiangyinzuo.carbon.dao.cache;

import pers.jiangyinzuo.carbon.domain.entity.QuestProgress;

/**
 * @author Jiang Yinzuo
 */
public interface QuestCache {

    /**
     * 根据用户ID获取用户的每日任务进度
     * @param userId 用户ID
     * @return 任务进度实体类
     */
    QuestProgress getQuestProgress(Long userId);
}
