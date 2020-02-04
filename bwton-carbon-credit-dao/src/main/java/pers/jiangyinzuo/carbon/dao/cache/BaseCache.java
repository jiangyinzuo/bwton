package pers.jiangyinzuo.carbon.dao.cache;

import io.lettuce.core.api.StatefulRedisConnection;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Jiang Yinzuo
 */
public abstract class BaseCache {
    protected StatefulRedisConnection<String, String> conn;

    @Autowired
    public void setConn(StatefulRedisConnection<String, String> conn) {
        this.conn = conn;
    }
}
