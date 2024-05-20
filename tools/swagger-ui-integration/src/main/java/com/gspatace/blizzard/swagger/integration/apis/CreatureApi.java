package com.gspatace.blizzard.swagger.integration.apis;

import com.gspatace.blizzard.swagger.integration.intf.DiscoverableResource;
import com.gspatace.blizzard.swagger.integration.intf.SwaggerResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SwaggerResource
public class CreatureApi extends AbstractApi implements DiscoverableResource {

    private static final String SPECIFICATION_FILE = "wow_creature_api_openapi_spec.yaml";

    @Override
    public String getName() {
        return "WoW Creature";
    }

    @Override
    @GetMapping("/v3/api-docs/wow-creature")
    public String getOpenApiSpec() {
        return getContent(SPECIFICATION_FILE);
    }
}
