package pers.jiangyinzuo.carbon.domain.vo;

import pers.jiangyinzuo.carbon.common.StringUtil;

import java.util.ArrayList;
import java.util.List;

import static pers.jiangyinzuo.carbon.domain.dto.PickCreditDropDTO.ADD_REC;
import static pers.jiangyinzuo.carbon.domain.dto.PickCreditDropDTO.SUB_REC;

/**
 * @author Jiang Yinzuo
 * @see pers.jiangyinzuo.carbon.domain.dto.PickCreditDropDTO
 */
public record PickedRecordVO(
        // 采摘动作
        PICK_ACTION pickAction,

        // 积分小水滴值
        Integer dropValue,

        // 采摘日期"yyyy-mm-dd"
        String date) {

    enum PICK_ACTION {
        /**
         * 采摘动作：自己摘、好友帮助、好友偷走
         */
        SELF, HELP, STOLEN
    }

    /**
     * 将redis中保存的原始记录转为PickedRecordVO
     *
     * @param queriedUserId 被查询记录的用户ID
     * @param rawResults redis中的原始记录
     * @return PickedRecordVO
     */
    public static PickedRecordVO createRecord(Long queriedUserId, String rawResults) {

        // 原始记录的格式为`{采摘者ID}["+", "-"]{小水滴碳积分值}["+", "-"]{年-月-日}`
        String[] result;
        PICK_ACTION pickAction;
        Long pickerUserId;
        if (rawResults.contains(ADD_REC)) {
            result = rawResults.split("\\+", 3);
            pickerUserId = Long.parseLong(result[0]);
            if (pickerUserId.equals(queriedUserId)) {
                pickAction = PICK_ACTION.SELF;
            } else {
                pickAction = PICK_ACTION.HELP;
            }
        } else {
            result = rawResults.split(SUB_REC, 3);
            pickAction = PICK_ACTION.STOLEN;
        }
        return new PickedRecordVO(pickAction, StringUtil.toInteger(result[1]), result[2]);
    }

    public static List<PickedRecordVO> createRecords(Long queriedUserId, List<String> rawResults) {
        List<PickedRecordVO> pickedRecords = new ArrayList<>(rawResults.size());
        for (String rawResult : rawResults) {
            pickedRecords.add(createRecord(queriedUserId, rawResult));
        }
        return pickedRecords;
    }
}
