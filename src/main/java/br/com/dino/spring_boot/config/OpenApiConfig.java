package br.com.dino.spring_boot.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI customOpenAPI(){
        return new OpenAPI().info(
            new Info()
                .title("Swagger OPEN AI")
                .version("v1")
                .termsOfService("Recordar para apreender")
                .license(new License()
                    .name("Apache 2")
                    .url("")
                )
        );
    }

}
