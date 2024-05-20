package com.gspatace.blizzard.swagger.integration.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfiguration {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Blizzard REST APIs")
                        .description("Swagger UI over Blizzard exposed APIs. Select an API from the upper-right corner dropdown.").version("v0.0.1")
                        .license(new License().name("MIT")))
                .externalDocs(new ExternalDocumentation()
                        .description("Check me on GitHub")
                        .url("https://github.com/gpspatacean/blizzard_rest_apis"));
    }

    @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                .group("OpenAPI spec files")
                .pathsToMatch("/v3/api-docs/**")
                .build();
    }
}
