package pers.jiangyinzuo.carbon.domain.entity;

import lombok.Data;

/**
 * @author Jiang Yinzuo
 */
@Data
public class PickedRecord {
    enum PICK_ACTION {
        /**
         * 采摘动作：自己摘、好友帮助、好友偷走
         */
        SELF, HELP, STOLEN
    }

    public PICK_ACTION pickAction;

    public Integer dropValue;
}
