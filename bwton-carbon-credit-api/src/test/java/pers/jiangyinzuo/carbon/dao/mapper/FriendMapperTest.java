package pers.jiangyinzuo.carbon.dao.mapper;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.Set;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class FriendMapperTest {
    @Autowired
    private FriendMapper friendMapper;

    @Test
    public void testAddFriend() {
        friendMapper.addFriends(2L, 4L);
        friendMapper.addFriends(3L, 5L);
        Set<Long> result = friendMapper.getFriends(2L);
//        assertEquals(2, result.size());
    }
}
