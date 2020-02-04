package pers.jiangyinzuo.carbon.dao.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class FriendMapperTest extends BaseMybatisTest {
    @Autowired
    private FriendMapper friendMapper;

    @Test
    public void testDuplicateInsert() {
        assertThrows(DuplicateKeyException.class, () -> {
            friendMapper.addFriends(4L, 5L);
            friendMapper.addFriends(4L, 5L);
        });
    }

    @Test
    public void testGetFriends() {
        assertEquals(4, friendMapper.getUserAndFriends(1L).size());
    }
}
