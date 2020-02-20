package pers.jiangyinzuo.carbon;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import pers.jiangyinzuo.carbon.config.RabbitmqConfig;

@SpringBootApplication(scanBasePackages = {"pers.jiangyinzuo.carbon.mq"}, scanBasePackageClasses = {RabbitmqConfig.class},
        exclude = {DataSourceAutoConfiguration.class})
public class TestApplication {
}
