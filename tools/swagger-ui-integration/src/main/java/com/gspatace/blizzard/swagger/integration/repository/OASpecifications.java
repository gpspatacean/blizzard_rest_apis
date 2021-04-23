package com.gspatace.blizzard.swagger.integration.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@Slf4j
public class OASpecifications {
    private final Map<String, String> specMap = new HashMap<>();

    private OASpecifications() {
    }

    public static OASpecifications getInstance() {
        return HOLDER.INSTANCE;
    }

    public Optional<String> getSpecification(String specificationFile) {
        if (specMap.containsKey(specificationFile)) {
            return Optional.of(specMap.get(specificationFile));
        }

        log.info("Loading specification file input: {}", specificationFile);
        final StringBuilder specification = new StringBuilder();
        final Resource resource = new ClassPathResource(specificationFile);
        try (Stream<String> lines = new BufferedReader(new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8)).lines()) {
            lines.forEach(line -> specification.append(line).append(System.lineSeparator()));
            final String contents = specification.toString();
            specMap.put(specificationFile, contents);
            log.trace("{} file content: {}", specificationFile, contents);
            return Optional.of(contents);
        } catch (IOException ex) {
            log.error("Exception occurred while parsing specification file {} :", specificationFile, ex);
            return Optional.empty();
        }
    }

    private static class HOLDER {
        public static final OASpecifications INSTANCE = new OASpecifications();
    }
}
