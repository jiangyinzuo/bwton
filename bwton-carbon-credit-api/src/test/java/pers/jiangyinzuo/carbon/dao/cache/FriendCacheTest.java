package pers.jiangyinzuo.carbon.dao.cache;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class FriendCacheTest {
    private FriendCache friendCache;

    @Autowired
    public void setFriendCache(FriendCache friendCache) {
        this.friendCache = friendCache;
    }

    @Test
    public void testAddFriend() {
        assertTrue(friendCache.addFriend("1", "2"));
        assertFalse(friendCache.addFriend(Long.toString(Long.MAX_VALUE), Long.toString(Long.MAX_VALUE - 1)));
    }
}
