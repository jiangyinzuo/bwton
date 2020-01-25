package pers.jiangyinzuo.carbon.dao.mapper;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.dao.DuplicateKeyException;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FriendMapperTest {
    @Autowired
    private FriendMapper friendMapper;

    @Test
    public void testAddFriend() {
        friendMapper.addFriends(10L, 24L);
        friendMapper.addFriends(10L, 25L);
        Set<Long> result = friendMapper.getFriends(10L);
        assertEquals(2, result.size());
        assertTrue(result.contains(24L));
        assertTrue(result.contains(25L));
    }

    @Test
    public void testDuplicateInsert() {
        assertThrows(DuplicateKeyException.class, () -> {
            friendMapper.addFriends(4L, 5L);
            friendMapper.addFriends(4L, 5L);
        });
    }
}
