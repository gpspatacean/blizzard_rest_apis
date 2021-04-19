package com.gspatace.blizzard.swagger.integration.configuration;

import com.gspatace.blizzard.swagger.integration.model.ResourceData;
import com.gspatace.blizzard.swagger.integration.services.ApiDiscoveryService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger.web.InMemorySwaggerResourcesProvider;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SwaggerUIEndpointsConfiguration {

    @Primary
    @Bean
    public SwaggerResourcesProvider swaggerResourcesProvider() {
        final ApiDiscoveryService apiDiscoveryService = new ApiDiscoveryService();
        final List<ResourceData> apis = apiDiscoveryService.getResources();
        return () -> {
            final List<SwaggerResource> resources = new ArrayList<>();

            apis.stream().forEach( res -> {
                final SwaggerResource swaggerResource = new SwaggerResource();
                swaggerResource.setName(res.getName());
                swaggerResource.setLocation(res.getEndpoint());
                resources.add(swaggerResource);
            });

            return resources;
        };
    }
}
