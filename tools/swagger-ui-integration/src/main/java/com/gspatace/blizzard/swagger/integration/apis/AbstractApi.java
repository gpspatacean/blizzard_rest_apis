package com.gspatace.blizzard.swagger.integration.apis;

import com.gspatace.blizzard.swagger.integration.repository.OASpecifications;

public abstract class AbstractApi {

    protected String getContent(String specificationFile) {
        return OASpecifications.getInstance().getSpecification(specificationFile).orElse("");
    }
}
