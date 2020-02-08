package pers.jiangyinzuo.carbon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.jiangyinzuo.carbon.dao.cache.QuestCache;
import pers.jiangyinzuo.carbon.domain.entity.QuestProgress;
import pers.jiangyinzuo.carbon.service.QuestService;

/**
 * @author Jiang Yinzuo
 */
@Service
public class QuestServiceImpl implements QuestService {

    private QuestCache questCache;

    @Autowired
    public QuestServiceImpl(QuestCache questCache) {
        this.questCache = questCache;
    }

    @Override
    public QuestProgress getQuestProgress(Long userId) {
        return questCache.getQuestProgress(userId);
    }
}
