package pers.jiangyinzuo.carbon.dao.cache;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pers.jiangyinzuo.carbon.domain.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserCacheTest {

    @Autowired
    private UserCache userCache;

    @Test
    public void testSetUsersAsync() {
        System.out.println(Thread.activeCount());
        userCache.setUsersAsync(List.of(new User(3L, "Bob", 3L)));
        System.out.println(Thread.activeCount());
    }

    @Test
    public void testGetFriends() {
        Set<Long> set = Set.of(3L, 1L, 5L);

        List<Object> result = userCache.getUsers(set);
        assertNotNull(result);
    }
}
