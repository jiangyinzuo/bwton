package pers.jiangyinzuo.carbon.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pers.jiangyinzuo.carbon.domain.entity.Prop;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
public class PropServiceTest {

    @Autowired
    private PropService propService;

    @Test
    public void testGetPropService() {
        Prop prop = propService.getPropById(0L, 999L);
        assertNull(prop);
    }

    @Test
    public void testCover() {
        propService.coverDrops(1L);
        assertFalse(propService.coverDrops(1L));
    }
}
