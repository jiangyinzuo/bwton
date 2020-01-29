package pers.jiangyinzuo.carbon;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"pers.jiangyinzuo.carbon.dao.mapper"})
public class TestApplication {}
