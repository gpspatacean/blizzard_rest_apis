package com.gspatace.blizzard.swagger.integration.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class ResourceData {
    private final String name;
    private final String endpoint;
    private final String openApiSpec;
}
