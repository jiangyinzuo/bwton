package pers.jiangyinzuo.carbon.dao.cache;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class FriendCacheTest {
    private FriendCache friendCache;

    @Autowired
    public void setFriendCache(FriendCache friendCache) {
        this.friendCache = friendCache;
    }

    @Test
    public void testAddFriend() {
        assertTrue(friendCache.addFriend(1L, 2L));
        assertFalse(friendCache.addFriend(Long.MAX_VALUE, Long.MAX_VALUE - 1));
    }

    @Test
    public void testGetFriendsId() {
        assertNull(friendCache.getFriendsId(Long.MAX_VALUE));
    }
}
