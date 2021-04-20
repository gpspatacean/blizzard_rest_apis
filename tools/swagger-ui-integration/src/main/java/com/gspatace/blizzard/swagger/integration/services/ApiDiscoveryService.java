package com.gspatace.blizzard.swagger.integration.services;

import com.gspatace.blizzard.swagger.integration.intf.DiscoverableResource;
import com.gspatace.blizzard.swagger.integration.intf.SwaggerResource;
import com.gspatace.blizzard.swagger.integration.model.ResourceData;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.springframework.web.bind.annotation.GetMapping;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

@Slf4j
public class ApiDiscoveryService {
    public List<ResourceData> getResources() {
        final List<ResourceData> resourceDataList = new ArrayList<>();
        final Reflections reflections = new Reflections("com.gspatace.blizzard.swagger.integration.apis");
        final Set<Class<?>> apis = reflections.getTypesAnnotatedWith(SwaggerResource.class);
        apis.forEach(clazz -> {
            final Optional<ResourceData> resourceData = getResourceDataFromClazz(clazz);
            resourceData.ifPresent(res -> {
                resourceDataList.add(res);
                log.info("Registered API {} at {} path.", res.getName(), res.getEndpoint());
            });
        });
        return resourceDataList;
    }

    private Optional<ResourceData> getResourceDataFromClazz(Class<?> clazz) {
        try {
            final Constructor<?> constructor = clazz.getConstructor();
            final DiscoverableResource api = (DiscoverableResource) constructor.newInstance();
            final Method method = clazz.getMethod("getOpenApiSpec");
            final GetMapping annotation = method.getAnnotation(GetMapping.class);
            final String endpoint = Arrays.stream(annotation.value()).findFirst().orElse("");
            return Optional.of(ResourceData.builder().name(api.getName()).endpoint(endpoint).openApiSpec(api.getOpenApiSpec()).build());
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException ex) {
            log.error("Exception occurred while parsing Discovered Class {}", clazz.getName(), ex);
            return Optional.empty();
        }
    }
}
