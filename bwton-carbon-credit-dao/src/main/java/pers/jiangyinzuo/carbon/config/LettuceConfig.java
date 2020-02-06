package pers.jiangyinzuo.carbon.config;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.resource.ClientResources;
import io.lettuce.core.resource.DefaultClientResources;
import lombok.Data;
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

    @Bean
    public ClientResources clientResources() {
        return DefaultClientResources.create();
    }

    @Bean
    public RedisURI singleRedisUri() {
        return RedisURI.create(lettuceProperties.getHost(), lettuceProperties.getPort());
    }

    @Bean(destroyMethod = "shutdownAsync")
    public RedisClient singleRedisClient(ClientResources clientResources, RedisURI singleRedisUri) {
        return RedisClient.create(clientResources, singleRedisUri);
    }

    @Bean
    public StatefulRedisConnection<String, String> redisConnectionStr(RedisClient redisClient) {
        return redisClient.connect();
    }
}

@ConfigurationProperties(prefix = "lettuce")
@Data
class LettuceConfigProperties {
    private String host;
    private Integer port;
}