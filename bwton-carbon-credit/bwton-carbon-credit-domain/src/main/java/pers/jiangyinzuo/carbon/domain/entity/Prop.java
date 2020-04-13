package pers.jiangyinzuo.carbon.domain.entity;


/**
 * 道具
 *
 * @author Jiang Yinzuo
 */
public record Prop(Long propId, String propName, Long propCount){
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
}
