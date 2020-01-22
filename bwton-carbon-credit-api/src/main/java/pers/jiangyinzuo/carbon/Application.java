package pers.jiangyinzuo.carbon;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Jiang Yinzuo
 */
@SpringBootApplication
@MapperScan(basePackages = {"pers.jiangyinzuo.carbon.dao.mapper"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
