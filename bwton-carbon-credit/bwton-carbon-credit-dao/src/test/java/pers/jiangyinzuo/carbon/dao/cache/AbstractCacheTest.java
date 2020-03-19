package pers.jiangyinzuo.carbon.dao.cache;

import io.lettuce.core.api.StatefulRedisConnection;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractCacheTest {

    @Autowired
    protected StatefulRedisConnection<String, String> conn;
}
