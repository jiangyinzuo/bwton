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
    public void testGetFriendsId() {
        assertNull(friendCache.getFriendsId(Long.MAX_VALUE));
    }
}
