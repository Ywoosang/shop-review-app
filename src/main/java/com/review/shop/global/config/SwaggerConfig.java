package com.review.shop.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    private static final String SECURITY_SCHEME_NAME = "JWT";

    @Bean
    public OpenAPI openApi() {
        return new OpenAPI()
                .info(apiInfo())
                .addSecurityItem(apiSecurityRequirement())
                .components(apiComponents());

    }

    private Info apiInfo() {
        return new Info()
                .title("API DOCS")
                .description("Shop Review APP Swagger UI")
                .version("1.0.0");
    }

    private SecurityRequirement apiSecurityRequirement() {
        return new SecurityRequirement().addList(SECURITY_SCHEME_NAME);
    }

    private Components apiComponents() {
        return new Components().addSecuritySchemes(SECURITY_SCHEME_NAME, new SecurityScheme()
                .name(SECURITY_SCHEME_NAME)
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
        );
    }
}
