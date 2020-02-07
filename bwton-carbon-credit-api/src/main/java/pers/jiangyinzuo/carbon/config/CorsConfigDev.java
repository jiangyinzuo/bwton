package pers.jiangyinzuo.carbon.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * 开发环境下的跨域设置
 *
 * @author Jiang Yinzuo
 */
@Configuration
@ConditionalOnProperty(prefix = "spring.profiles", name = "active", havingValue = "dev")
public class CorsConfigDev extends WebMvcConfigurationSupport {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowedOrigins("http://localhost:4040", "http://localhost:5000", "http://localhost:8080", "http://47.95.204.239:8000");
        super.addCorsMappings(registry);
    }
}
