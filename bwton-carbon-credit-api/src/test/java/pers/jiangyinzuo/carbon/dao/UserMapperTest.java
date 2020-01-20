package pers.jiangyinzuo.carbon.dao;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.dao.DuplicateKeyException;
import pers.jiangyinzuo.carbon.common.security.SaltGenerator;
import pers.jiangyinzuo.carbon.domain.dto.UserAccountDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testLogin() {
        UserAccountDTO userAccountDTO = userMapper.getUserAccountByTelephone("13012345678");
        assertEquals("13012345678", userAccountDTO.getTelephone());
        assertEquals("abc", userAccountDTO.getPassword());
    }

    @Test
    public void testRegister() {
        assertThrows(DuplicateKeyException.class, () -> {
            userMapper.saveUserAccount("李四", "123456", SaltGenerator.getSalt32(), "13012345678");
        });
    }
}
