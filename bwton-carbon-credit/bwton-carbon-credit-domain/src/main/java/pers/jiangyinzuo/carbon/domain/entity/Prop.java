package pers.jiangyinzuo.carbon.domain.entity;


import lombok.Data;

/**
 * 道具
 *
 * @author Jiang Yinzuo
 */
@Data
public class Prop {

    public enum PROP_ID {
        /**
         * 道具ID的枚举
         */
        SPEEDER(1L), COVER(2L), SCHEDULE_CARD(3L), LOTTERY(4L), RE_SIGN_IN_CARD(5L);
        public final Long id;

        PROP_ID(long propId) {
            id = propId;
        }
    }

    private Long propId;
    private String propName;
    private Long propCount;
}
