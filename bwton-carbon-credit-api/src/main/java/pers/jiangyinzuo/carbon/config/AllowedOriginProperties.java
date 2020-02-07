package pers.jiangyinzuo.carbon.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Jiang Yinzuo
 */
@ConfigurationProperties(prefix = "http")
@Data
public class AllowedOriginProperties {
    private String[] allowedOrigins;
}
