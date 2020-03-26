package pers.jiangyinzuo.carbon.dao.cache;

import io.lettuce.core.api.StatefulRedisConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public abstract class AbstractCacheTest {

    @Autowired
    protected StatefulRedisConnection<String, String> conn;
}
