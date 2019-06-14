package com.fisher.arch.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, new ArrayList<>(responseMessages()))
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.ant("/index/**"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("xx Api文档")
                .description("工具Api文档")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }


    private ArrayList<ResponseMessage> responseMessages() {
        ArrayList<ResponseMessage> objects = new ArrayList<>();
        objects.add(
                new ResponseMessageBuilder()
                .code(500)
                        .message("500 message")
                        .build()
        );

        objects.add(
                new ResponseMessageBuilder()
                        .message("403 forbidden")
                        .code(403)
                .build()
        );

        return objects;
    }


}
