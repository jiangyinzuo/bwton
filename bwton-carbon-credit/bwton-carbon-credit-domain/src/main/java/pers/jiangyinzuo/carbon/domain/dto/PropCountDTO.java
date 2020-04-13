package pers.jiangyinzuo.carbon.domain.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static pers.jiangyinzuo.carbon.domain.entity.Prop.PROP_ID.*;

/**
 * 道具数量DTO,记录每个道具ID对应的数量
 *
 * @author Jiang Yinzuo
 *
 * @see pers.jiangyinzuo.carbon.domain.entity.Prop
 */
public class PropCountDTO {

    private final List<Prop> props = new ArrayList<>();

    /**
     * 签到奖励
     */
    public static final Map<Integer, PropCountDTO> REWARD_MAP = Map.of(
            7, new PropCountDTO().plusProp(SPEEDER.id, 1L),
            10, new PropCountDTO().plusProp(COVER.id, 1L).plusProp(SCHEDULE_CARD.id, 1L),
            15, new PropCountDTO().plusProp(COVER.id, 1L).plusProp(LOTTERY.id, 1L).plusProp(SCHEDULE_CARD.id, 1L)
    );

    /**
     * 月底奖励
     */
    public static final PropCountDTO END_REWARD = new PropCountDTO()
            .plusProp(RE_SIGN_IN_CARD.id, 3L)
            .plusProp(COVER.id, 3L)
            .plusProp(SPEEDER.id, 3L)
            .plusProp(LOTTERY.id, 2L)
            .plusProp(SCHEDULE_CARD.id, 2L);

    public static class Prop {
        public final Long propId;
        private Long propCount;

        public Prop(Long propId, Long propCount) {
            this.propId = propId;
            this.propCount = propCount;
        }
    }

    public PropCountDTO plusProp(Long propId, Long propCount) {
        return plusProp(new Prop(propId, propCount));
    }

    public PropCountDTO plusProp(Prop prop) {
        for (Prop i : props) {
            if (i.propId.equals(prop.propId)) {
                i.propCount += prop.propCount;
                return this;
            }
        }
        props.add(prop);
        return this;
    }

    public PropCountDTO plusProps(PropCountDTO propCountDTO) {
        for (Prop prop : propCountDTO.props) {
            plusProp(prop);
        }
        return this;
    }

    public List<Prop> getProps() {
        return props;
    }

    @Override
    public String toString() {
        return "PropCountDTO{" +
                "props=" + props +
                '}';
    }
}
