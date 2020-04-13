package pers.jiangyinzuo.carbon.dao.cache.impl;

import org.springframework.stereotype.Repository;
import pers.jiangyinzuo.carbon.common.StringUtil;
import pers.jiangyinzuo.carbon.dao.cache.BaseCache;
import pers.jiangyinzuo.carbon.dao.cache.QuestCache;
import pers.jiangyinzuo.carbon.domain.entity.QuestProgress;

import java.util.Map;

import static pers.jiangyinzuo.carbon.dao.cache.RedisKeyBuilder.userQuestProgress;

/**
 * @author Jiang Yinzuo
 */
@Repository
public class QuestCacheImpl extends BaseCache implements QuestCache {

    @Override
    public QuestProgress getQuestProgress(Long userId) {
        Map<String, String> result = cmdSync.hgetall(userQuestProgress(userId));
        if (result == null) {
            return new QuestProgress(0, 0, 0);
        }
        return new QuestProgress(
                StringUtil.toInteger(result.get("collectCredit")),
                StringUtil.toInteger(result.get("signIn")),
                StringUtil.toInteger(result.get("assistCollecting"))
        );
    }
}
