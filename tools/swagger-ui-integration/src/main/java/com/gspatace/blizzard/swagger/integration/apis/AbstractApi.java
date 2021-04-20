package com.gspatace.blizzard.swagger.integration.apis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

@Slf4j
public abstract class AbstractApi {
    protected String getContent(String specificationFile) {
        log.info("Loading specification file input: {}", specificationFile);
        StringBuilder specification = new StringBuilder();
        Resource resource = new ClassPathResource(specificationFile);
        try (Stream<String> lines = new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)).lines()) {
            lines.forEach(line -> specification.append(line).append(System.lineSeparator()));
            log.trace("{} file content: {}", specificationFile, specification.toString());
        } catch (IOException ex) {
            log.error("Exception occurred while parsing specification file {} :", specificationFile, ex);
        }
        return specification.toString();
    }
}
