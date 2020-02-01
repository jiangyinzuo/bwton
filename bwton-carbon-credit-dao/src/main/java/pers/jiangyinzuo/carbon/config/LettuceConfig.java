package pers.jiangyinzuo.carbon.config;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.resource.ClientResources;
import io.lettuce.core.resource.DefaultClientResources;
import lombok.Data;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Jiang Yinzuo
 */
@Configuration
@EnableConfigurationProperties(LettuceConfigProperties.class)
public class LettuceConfig {
    private final LettuceConfigProperties lettuceProperties;

    public LettuceConfig(LettuceConfigProperties lettuceProperties) {
        this.lettuceProperties = lettuceProperties;
    }

    @Bean(destroyMethod = "shutdown")
    public ClientResources clientResources() {
        return DefaultClientResources.create();
    }

    @Bean
    public RedisURI singleRedisUri() {
        return RedisURI.create(lettuceProperties.getHost(), lettuceProperties.getPort());
    }

    @Bean(destroyMethod = "shutdown")
    public RedisClient singleRedisClient(ClientResources clientResources, @Qualifier("singleRedisUri") RedisURI redisUri) {
        return RedisClient.create(clientResources, redisUri);
    }

    @Bean(destroyMethod = "close")
    public StatefulRedisConnection<String, String> singleRedisConnection(@Qualifier("singleRedisClient") RedisClient singleRedisClient) {
        return singleRedisClient.connect();
    }
}


@ConfigurationProperties(prefix = "lettuce")
@Data
class LettuceConfigProperties {
    private String host;
    private Integer port;
}