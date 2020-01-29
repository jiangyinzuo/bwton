package pers.jiangyinzuo.carbon.dao.cache;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@SpringBootTest
public class LeaderBoardCacheTest {
    @Autowired
    private LeaderBoardCache leaderBoardCache;

    @Test
    public void testGetLeaderBoard() {
        Set<Long> ids = new HashSet<>(Arrays.asList(2L, 3L, 1L));
        leaderBoardCache.getLeaderBoard(ids);
    }
}
