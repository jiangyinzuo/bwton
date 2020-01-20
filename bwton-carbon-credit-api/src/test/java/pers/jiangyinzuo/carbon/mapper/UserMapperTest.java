package pers.jiangyinzuo.carbon.mapper;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import pers.jiangyinzuo.carbon.domain.dto.UserAccountDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testLogin() {
        assertNotNull(userMapper);
        UserAccountDTO userAccountDTO = userMapper.getUserAccountByUserId(1L);
        assertEquals("13012345678", userAccountDTO.getTelephone());
        assertEquals("abc", userAccountDTO.getPassword());
    }
}
