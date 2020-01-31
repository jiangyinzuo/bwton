package pers.jiangyinzuo.carbon.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LeaderboardServiceTest {

    @Autowired
    private LeaderboardService leaderboardService;

    @Test
    public void testGetTotalLeaderboard() throws ExecutionException, InterruptedException {
        var result = leaderboardService.getTotalLeaderBoard(1L);
        assertEquals(1L, result.getUser().getUserId());
    }
}
