package pers.jiangyinzuo.carbon.dao.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;


public class CreditMapperTest extends BaseMybatisTest {

    @Autowired
    private CreditMapper creditMapper;

    @Test
    public void testGetHistoryTotalCredits() {
        var result = creditMapper.getHistoryTotalCredits(List.of(1L, 5L));
        assertArrayEquals(List.of(123L, 444L).toArray(), result.toArray());
    }
}
