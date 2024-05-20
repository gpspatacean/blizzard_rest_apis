package com.gspatace.blizzard.swagger.integration.configuration;

import com.gspatace.blizzard.swagger.integration.model.ResourceData;
import com.gspatace.blizzard.swagger.integration.services.ApiDiscoveryService;
import org.springdoc.core.properties.AbstractSwaggerUiConfigProperties;
import org.springdoc.core.properties.SwaggerUiConfigParameters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Configuration
public class SwaggerUIEndpointsConfiguration {

    @Bean
    public Set<AbstractSwaggerUiConfigProperties.SwaggerUrl> registerAPIs(final SwaggerUiConfigParameters swaggerUiConfigParameters) {
        final ApiDiscoveryService apiDiscoveryService = new ApiDiscoveryService();
        final List<ResourceData> resources = apiDiscoveryService.getResources();

        final Set<AbstractSwaggerUiConfigProperties.SwaggerUrl> registeredApis = resources.stream().map(resource -> {
            final AbstractSwaggerUiConfigProperties.SwaggerUrl swaggerUrl = new AbstractSwaggerUiConfigProperties.SwaggerUrl();
            swaggerUrl.setName(resource.getEndpoint().replace("/v3/api-docs/", ""));
            swaggerUrl.setDisplayName(resource.getName());
            return swaggerUrl;
        }).collect(Collectors.toSet());
        swaggerUiConfigParameters.setUrls(registeredApis);
        return registeredApis;
    }
}
