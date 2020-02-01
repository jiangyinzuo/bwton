package pers.jiangyinzuo.carbon.dao.cache;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pers.jiangyinzuo.carbon.domain.entity.User;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserCacheTest {

    @Autowired
    private UserCache userCache;

    @Test
    public void testSetUsersAsync() {
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 100; ++i) {
            userCache.setUsersAsync(List.of(new User((long)i, "Bob", (long)i)));
        }
        System.out.println(System.currentTimeMillis() - startTime);
    }

    @Test
    public void testGetFriends() {
        Set<Long> set = Set.of(1098L, 386L, 325L);

        List<Map<String, String>> result = null;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 50; ++i) {
            result = userCache.getUsers(set);
        }
        System.out.println(System.currentTimeMillis() - startTime);

        assertNotNull(result);
    }
}
