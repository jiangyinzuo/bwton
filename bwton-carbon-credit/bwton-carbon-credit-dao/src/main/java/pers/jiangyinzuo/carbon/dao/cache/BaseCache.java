package pers.jiangyinzuo.carbon.dao.cache;

import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.sync.RedisCommands;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Jiang Yinzuo
 */
public abstract class BaseCache {

    protected StatefulRedisConnection<String, String> conn;
    protected RedisCommands<String, String> cmdSync;
    protected RedisAsyncCommands<String, String> cmdAsync;

    @Autowired
    public void setConn(StatefulRedisConnection<String, String> conn) {
        this.conn = conn;
        this.cmdSync = conn.sync();
        this.cmdAsync = conn.async();
    }
}
