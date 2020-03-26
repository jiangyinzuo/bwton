package pers.jiangyinzuo.carbon.dao.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import pers.jiangyinzuo.carbon.domain.dto.PropCountDTO;
import pers.jiangyinzuo.carbon.domain.entity.Prop;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PropMapperTest extends BaseMybatisTest {

    @Autowired
    private PropMapper propMapper;

    @Test
    public void testGetProps() {
        List<Prop> props = propMapper.getProps(Long.MAX_VALUE);
        assertEquals(0, props.size());

        List<Prop> propsBefore = propMapper.getProps(1L);
        propMapper.addPropsCount(1L, PropCountDTO.END_REWARD.getProps());
        List<Prop> propsAfter = propMapper.getProps(1L);
        System.out.println(propsBefore);
        System.out.println(propsAfter);
        for (Prop prop1 : propsAfter) {
            for (Prop prop2 : propsBefore) {
                if (prop1.getPropId().equals(prop2.getPropId())) {
                    assertTrue(prop2.getPropCount() < prop1.getPropCount());
                }
            }
        }
    }

    @Test
    public void testDecrPropCount() {
        PropCountDTO propCountDTO = new PropCountDTO();
        propCountDTO.plusProp(2L, 2L);
        propMapper.addPropsCount(1L, propCountDTO.getProps());

        Prop propBefore = propMapper.getPropById(1L, 2L);

        if (propBefore.getPropCount() > 0) {
            propMapper.decrPropsCount(1L, 2L, 1);
        }
        Prop propAfter = propMapper.getPropById(1L, 2L);

        if (propBefore.getPropCount() > 0) {
            assertEquals(-1, propAfter.getPropCount() - propBefore.getPropCount());
        }
    }
}
