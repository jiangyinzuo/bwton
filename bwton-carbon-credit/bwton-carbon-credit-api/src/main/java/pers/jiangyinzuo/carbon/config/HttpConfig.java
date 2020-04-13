package pers.jiangyinzuo.carbon.config;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

/**
 * @author Jiang Yinzuo
 */
@Configuration
@EnableConfigurationProperties(AllowedOriginProperties.class)
public class HttpConfig extends WebMvcConfigurationSupport {

    private final AllowedOriginProperties allowedOriginProperties;

    public HttpConfig(AllowedOriginProperties allowedOriginProperties) {
        this.allowedOriginProperties = allowedOriginProperties;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowCredentials(true)
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

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(converter());
    }

    /**
     * 配置ObjectMapper, 使Jackson能反序列化/序列化final fields
     * @return MappingJackson2HttpMessageConverter
     */
    @Bean
    public MappingJackson2HttpMessageConverter converter() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new ParameterNamesModule());
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        return new MappingJackson2HttpMessageConverter(mapper);
    }
}

@EnableConfigurationProperties(AllowedOriginProperties.class)
class RefererInterceptor extends HandlerInterceptorAdapter {

    private final Set<String> allowedOrigins;

    RefererInterceptor(AllowedOriginProperties allowedOriginProperties) {
        allowedOrigins = Set.of(allowedOriginProperties.getAllowedOrigins());
    }

    /**
     * 验证referer 防盗链
     * @param request
     * @param response
     * @param handler
     * @return
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (allowedOrigins.contains("*")) {
            return true;
        }
        String referer = request.getHeader("referer");
        if (referer == null) {
            response.setStatus(404);
            return false;
        }
        for (String prefix : allowedOrigins) {
            if (referer.startsWith(prefix)) {
                return true;
            }
        }
        response.setStatus(404);
        return false;
    }
}
