package com.gspatace.blizzard.swagger.integration.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.gspatace.blizzard.swagger.integration.model.ResourceData;
import com.gspatace.blizzard.swagger.integration.repository.OASpecifications;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
public class ApiDiscoveryService {
    private final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

    public List<ResourceData> getResources() {
        final List<ResourceData> resourceDataList = new ArrayList<>();
        for (final String specFile : getSpecificationFiles()) {
            final String contents = OASpecifications.getInstance().getSpecification(specFile).orElse("");
            try {
                final String apiTitle = getApiTitle(contents);
                final ResourceData resourceData = ResourceData.builder().name(apiTitle).endpoint(specFile).openApiSpec(contents).build();
                resourceDataList.add(resourceData);
                log.info("Registered API {} at {} path.", resourceData.getName(), resourceData.getEndpoint());
            } catch (final JsonProcessingException e) {
                log.error("Exception occurred while parsing specification file {}", specFile, e);
            }
        }
        return resourceDataList;
    }

    private String getApiTitle(final String yamlContent) throws JsonProcessingException {
        final var yamlMap = mapper.readValue(yamlContent, Map.class);
        final var infoNode =  yamlMap.get("info");
        return (String) ((Map<String, Object>)infoNode).get("title");
    }

    private List<String> getSpecificationFiles() {
        final List<String> specFiles = new ArrayList<>();
        try {
            final Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath*:**/*_openapi_spec.yaml");
            for (final Resource resource : resources) {
                specFiles.add(resource.getFilename());
            }
        } catch (final IOException e) {
            log.error("Exception occurred while parsing specification files", e);
        }
        return specFiles;
    }
}
