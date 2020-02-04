package pers.jiangyinzuo.carbon.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LeaderboardServiceTest {

    @Autowired
    private LeaderboardService leaderboardService;

    @Test
    public void testGetTotalLeaderboard()  {
        var result = leaderboardService.getLeaderBoard(1L, LeaderboardService.Mode.TOTAL);
        assertEquals(1L, result.getUser().getUserId());
    }
}
