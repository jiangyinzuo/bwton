package pers.jiangyinzuo.carbon.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author Jiang Yinzuo
 */
@Configuration
@MapperScan("pers.jiangyinzuo.carbon.dao.mapper")
public class MybatisConfig {
}
