package pers.jiangyinzuo.carbon.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * 跨域设置
 *
 * @author Jiang Yinzuo
 */
@Configuration
@EnableConfigurationProperties(AllowedOriginProperties.class)
@ConditionalOnProperty(prefix = "spring.profiles", name = "active", havingValue = "dev")
public class HttpConfig extends WebMvcConfigurationSupport {

    private final AllowedOriginProperties allowedOriginProperties;

    public HttpConfig(AllowedOriginProperties allowedOriginProperties) {
        this.allowedOriginProperties = allowedOriginProperties;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowedOrigins(allowedOriginProperties.getAllowedOrigins());
        super.addCorsMappings(registry);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new RefererInterceptor(allowedOriginProperties))
                .addPathPatterns("/**");
    }
}

@EnableConfigurationProperties(AllowedOriginProperties.class)
class RefererInterceptor extends HandlerInterceptorAdapter {

    private final Set<String> allowedOrigins;

    RefererInterceptor(AllowedOriginProperties allowedOriginProperties) {
        allowedOrigins = Set.of(allowedOriginProperties.getAllowedOrigins());
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String referer = request.getHeader("referer");
        if (referer == null || !allowedOrigins.contains(referer)) {
            response.setStatus(404);
            return false;
        }
        return true;
    }
}
