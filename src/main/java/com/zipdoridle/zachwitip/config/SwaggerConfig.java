package com.zipdoridle.zachwitip.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableOpenApi
public class SwaggerConfig {

    @Bean
    public Docket oas(){
        return new Docket(DocumentationType.OAS_30)
                .groupName("zipdoridle")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zipdoridle.zachwitip.controller"))
                .paths(PathSelectors.ant("/**"))
                .build();
    }

}
