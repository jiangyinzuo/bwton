package pers.jiangyinzuo.carbon.dao.mapper;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.dao.DuplicateKeyException;
import pers.jiangyinzuo.carbon.common.security.SaltGenerator;
import pers.jiangyinzuo.carbon.domain.dto.UserLoginDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testLogin() {
        UserLoginDTO userLoginDTO = userMapper.getUserAccountByTelephone("13012345678");
        assertEquals("13012345678", userLoginDTO.getTelephone());
        assertEquals(64, userLoginDTO.getCipher().length());
    }

    @Test
    public void testRegister() {
        assertThrows(DuplicateKeyException.class, () -> userMapper.saveUserAccount("李四", "123456", SaltGenerator.getSalt32(), "13012345678"));
        userMapper.saveUserAccount("江胤佐", "222", SaltGenerator.getSalt32(), "13011145678");
    }
}
