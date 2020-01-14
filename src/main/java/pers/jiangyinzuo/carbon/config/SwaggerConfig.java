package pers.jiangyinzuo.carbon.config;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jiang Yinzuo
 */
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class SwaggerConfig {

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("八维通碳积分系统API文档")
                .contact(new Contact("江胤佐","https://github.com/ChiangYintso", "392711804@qq.com"))
                .version("20200107")
                .build();
    }

    @Bean
    public Docket createRestApi() {
        List<ResponseMessage> responseMessages = new ArrayList<>();
        responseMessages.add(new ResponseMessageBuilder().code(400).message(HttpStatus.BAD_REQUEST.getReasonPhrase()).responseModel(new ModelRef(null)).build());
        responseMessages.add(new ResponseMessageBuilder().code(404).message(HttpStatus.NOT_FOUND.getReasonPhrase()).responseModel(new ModelRef(null)).build());
        responseMessages.add(new ResponseMessageBuilder().code(500).message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()).responseModel(new ModelRef(null)).build());

        return new Docket(DocumentationType.SWAGGER_2)
                .globalResponseMessage(RequestMethod.GET, responseMessages)
                .globalResponseMessage(RequestMethod.POST, responseMessages)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("pers.jiangyinzuo.carbon.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}
