package pers.jiangyinzuo.carbon.dao.cache;

import io.lettuce.core.api.StatefulRedisConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Jiang Yinzuo
 */
@Repository
public abstract class AbstractCache {

    protected StatefulRedisConnection<String, String> redisConnection;

    @Autowired
    public void setRedisConnection(StatefulRedisConnection<String, String> redisConnection) {
        this.redisConnection = redisConnection;
    }
}
