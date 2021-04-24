package com.gspatace.blizzard.swagger.integration.apis;

import com.gspatace.blizzard.swagger.integration.intf.DiscoverableResource;
import com.gspatace.blizzard.swagger.integration.intf.SwaggerResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SwaggerResource
public class Oauth2API extends AbstractApi implements DiscoverableResource {

    private static final String SPECIFICATION_FILE= "blizzard_oauth2_openapi_spec.yaml";
    @Override
    public String getName() {
        return "Blizzard Oauth2";
    }

    @Override
    @GetMapping("/blizzard-oauth2")
    public String getOpenApiSpec() {
        return getContent(SPECIFICATION_FILE);
    }
}
