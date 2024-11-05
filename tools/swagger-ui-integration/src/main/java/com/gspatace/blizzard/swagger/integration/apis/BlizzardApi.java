package com.gspatace.blizzard.swagger.integration.apis;

import com.gspatace.blizzard.swagger.integration.repository.OASpecifications;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author George Spătăcean
 */
@RestController
public class BlizzardApi {

    @GetMapping(value = "/v3/api-docs/{apiFileName}")
    public String getOpenApiSpec(@PathVariable("apiFileName") final String apiFileName) {
        return OASpecifications.getInstance().getSpecification(apiFileName).orElse("");
    }
}
