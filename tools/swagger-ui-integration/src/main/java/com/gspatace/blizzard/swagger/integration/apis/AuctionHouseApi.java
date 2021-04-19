package com.gspatace.blizzard.swagger.integration.apis;

import com.gspatace.blizzard.swagger.integration.intf.DiscoverableResource;
import com.gspatace.blizzard.swagger.integration.intf.SwaggerResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SwaggerResource
public class AuctionHouseApi extends AbstractApi implements DiscoverableResource {

    private static final String SPECIFICATION_FILE = "auction_house_openapi_spec.yaml";

    @Override
    @GetMapping("/auction-house")
    public String getOpenApiSpec() {
        return getContent(SPECIFICATION_FILE);
    }

    @Override
    public String getName() {
        return "Auction House";
    }
}
