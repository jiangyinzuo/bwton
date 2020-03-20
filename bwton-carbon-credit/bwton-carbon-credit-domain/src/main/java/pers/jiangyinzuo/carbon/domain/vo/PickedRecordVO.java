package pers.jiangyinzuo.carbon.domain.vo;

import java.util.ArrayList;
import java.util.List;

import static pers.jiangyinzuo.carbon.domain.dto.PickCreditDropDTO.ADD_REC;
import static pers.jiangyinzuo.carbon.domain.dto.PickCreditDropDTO.SUB_REC;

/**
 * @author Jiang Yinzuo
 * @see pers.jiangyinzuo.carbon.domain.dto.PickCreditDropDTO
 */
public class PickedRecordVO {

    enum PICK_ACTION {
        /**
         * 采摘动作：自己摘、好友帮助、好友偷走
         */
        SELF, HELP, STOLEN
    }

    /**
     * 采摘动作
     */
    private PICK_ACTION pickAction;

    /**
     * 积分小水滴值
     */
    private Integer dropValue;

    /**
     * 采摘日期"yyyy-mm-dd"
     */
    private String date;

    /**
     * 将redis中保存的原始记录转为PickedRecordVO
     *
     * @param queriedUserId 被查询记录的用户ID
     * @param rawResults redis中的原始记录
     * @return PickedRecordVO
     */
    public static PickedRecordVO createRecord(Long queriedUserId, String rawResults) {
        PickedRecordVO record = new PickedRecordVO();

        // 原始记录的格式为`{采摘者ID}["+", "-"]{小水滴碳积分值}["+", "-"]{年-月-日}`
        String[] result;

        Long pickerUserId;
        if (rawResults.contains(ADD_REC)) {
            result = rawResults.split("\\+", 3);
            pickerUserId = Long.parseLong(result[0]);
            if (pickerUserId.equals(queriedUserId)) {
                record.pickAction = PICK_ACTION.SELF;
            } else {
                record.pickAction = PICK_ACTION.HELP;
            }
        } else {
            result = rawResults.split(SUB_REC, 3);
            record.pickAction = PICK_ACTION.STOLEN;
        }
        record.dropValue = Integer.parseInt(result[1]);
        record.date = result[2];
        return record;
    }

    public static List<PickedRecordVO> createRecords(Long queriedUserId, List<String> rawResults) {
        List<PickedRecordVO> pickedRecords = new ArrayList<>(rawResults.size());
        for (String rawResult : rawResults) {
            pickedRecords.add(createRecord(queriedUserId, rawResult));
        }
        return pickedRecords;
    }

    private PickedRecordVO() {}

    public PICK_ACTION getPickAction() {
        return pickAction;
    }

    public Integer getDropValue() {
        return dropValue;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "PickedRecordVO{" +
                "pickAction=" + pickAction +
                ", dropValue=" + dropValue +
                ", date='" + date + '\'' +
                '}';
    }
}
