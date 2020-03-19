package pers.jiangyinzuo.carbon.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pers.jiangyinzuo.carbon.domain.CREDIT_RECORD_MODE;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CreditServiceTest {

    @Autowired
    private CreditService creditService;

    @Test
    public void testGetTotalLeaderboard()  {
        var result = creditService.getLeaderBoard(1L, CREDIT_RECORD_MODE.TOTAL);
        assertNotNull(result);
    }
}
