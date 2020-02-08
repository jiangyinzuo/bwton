package pers.jiangyinzuo.carbon.domain.entity;

import pers.jiangyinzuo.carbon.common.StringUtil;
import lombok.Data;

import java.util.Map;

/**
 * @author Jiang Yinzuo
 */
@Data
public class QuestProgress {

    Integer collectCredit;
    Integer signIn;
    Integer assistCollecting;

    public QuestProgress() {}

    public QuestProgress(Map<String, String> rawData) {
        if (rawData == null) {
            collectCredit = 0;
            signIn = 0;
            assistCollecting = 0;
        } else {
            collectCredit = StringUtil.toInteger(rawData.get("collectCredit"));
            signIn = StringUtil.toInteger(rawData.get("signIn"));
            assistCollecting = StringUtil.toInteger(rawData.get("assistCollecting"));
        }
    }
}
