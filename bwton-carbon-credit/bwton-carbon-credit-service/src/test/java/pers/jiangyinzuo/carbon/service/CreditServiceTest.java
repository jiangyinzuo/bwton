package pers.jiangyinzuo.carbon.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pers.jiangyinzuo.carbon.dao.cache.CreditCache;
import pers.jiangyinzuo.carbon.domain.CREDIT_RECORD_MODE;
import pers.jiangyinzuo.carbon.domain.dto.PickCreditDropDTO;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CreditServiceTest {

    @Autowired
    private CreditService creditService;

    @Autowired
    private CreditCache creditCache;

    @Test
    public void testGetTotalLeaderboard()  {
        var result = creditService.getLeaderBoard(1L, CREDIT_RECORD_MODE.TOTAL);
        assertNotNull(result);
    }

    @Test
    public void testPickCreditDrop() {
        final Long pickedUserId = 1L;
        final Long pickerUserId = 3L;
        final long creditValue = 12L;

        long totalCreditBefore = creditService.getUserCredit(pickerUserId, CREDIT_RECORD_MODE.TOTAL);
        var dropsBefore = creditService.getCreditDrops(pickedUserId);

        // 添加一个立即成熟的积分小水滴
        creditCache.addCreditDropAsync(pickedUserId, creditValue, 0L);
        var drops = creditService.getCreditDrops(pickedUserId);

        // 断言刚添加了一个积分小水滴，数组长度增加或不变
        assertTrue(dropsBefore.size() <= drops.size());

        // 找到刚添加的积分小水滴
        String lastDropValue = null;
        for (String drop : drops) {
            if (drop.contains(".12")) {
                lastDropValue = drop;
            }
        }

        assertNotNull(lastDropValue);

        PickCreditDropDTO pickCreditDropDTO = new PickCreditDropDTO(pickerUserId, pickerUserId, pickedUserId, lastDropValue);

        boolean isPicked = creditService.pickCreditDrop(pickCreditDropDTO);

        // 断言积分小水滴采摘成功
        assertTrue(isPicked);

        var dropsAfter = creditService.getCreditDrops(pickedUserId);
        assertEquals(dropsBefore.size(), dropsAfter.size());

        long totalCreditAfter = creditService.getUserCredit(pickerUserId, CREDIT_RECORD_MODE.TOTAL);

        assertEquals(totalCreditBefore + creditValue, totalCreditAfter);
    }
}
