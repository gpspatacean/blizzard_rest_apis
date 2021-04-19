package com.gspatace.blizzard.swagger.integration.apis;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Slf4j
public abstract class AbstractApi {
    protected String getContent(String specificationFile) {
        StringBuilder specification = new StringBuilder();
        try (Stream<String> lines = Files.lines(Paths.get(ClassLoader.getSystemResource(specificationFile).toURI()))) {
            lines.forEach(line -> specification.append(line).append(System.lineSeparator()));
        } catch (URISyntaxException | IOException ex) {
            log.error("Exception occurred while parsing specification file {} :", specificationFile, ex);
        }
        return specification.toString();
    }
}
