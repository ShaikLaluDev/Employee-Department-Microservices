package com.stackly.department.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI departmentOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Department Service API")
                        .version("1.0")
                        .description("Department Management Microservice REST APIs"));
    }
}