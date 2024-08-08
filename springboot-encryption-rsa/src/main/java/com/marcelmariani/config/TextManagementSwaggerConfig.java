package com.marcelmariani.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class TextManagementSwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Text Raw Management")
                        .version("1.0.0")
                        .description("API to manager and store raw text data, using Java API Spring boot.")
                        .contact(new io.swagger.v3.oas.models.info.Contact()
                                .name("Marcel Mariani")
                                .url("https://www.linkedin.com/in/marcelmariani/")
                                .email("marcel.vazmariani@gmail.com")));
    }
}