package pers.jiangyinzuo.carbon.dao.cache;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pers.jiangyinzuo.carbon.domain.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        Set<Long> set = Set.of(3L, 4L, 1L, 5L);

        List<Object> result = userCache.getUsers(set);
        int idx = 0;
        for (Long i : set) {
            if (i.equals(1L) || i.equals(3L)) {
                assertEquals(2, ((Map)result.get(idx)).size());
                System.out.println(((Map<String, Object>)result.get(idx)).get("nickname"));
            } else {
                assertTrue(((Map)result.get(idx)).isEmpty());
            }
            idx++;
        }
    }
}
