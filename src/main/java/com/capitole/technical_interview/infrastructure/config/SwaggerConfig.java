package com.capitole.technical_interview.infrastructure.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Product Catalog API",
        version = "1.0",
        description = "API for managing the product catalog",
        contact = @Contact(
            name = "Support",
            email = "ikcontreras@outlook.es",
            url = "https://www.linkedin.com/in/ikcontreras/"
        )
    )
)

public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new io.swagger.v3.oas.models.info.Info()
                .title("Product Catalog API")
                .version("1.0")
                .description("API for managing product catalog")
                .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}
