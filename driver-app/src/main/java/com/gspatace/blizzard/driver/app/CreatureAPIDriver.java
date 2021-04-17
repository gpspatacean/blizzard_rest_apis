package com.gspatace.blizzard.driver.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gspatace.blizzard.wow.creature.api.CreatureApi;
import com.gspatace.blizzard.wow.creature.model.CreatureApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;

import com.gspatace.blizzard.wow.creature.ApiClient;
import org.springframework.stereotype.Component;

@Component
public class CreatureAPIDriver {

    public void doTest(String accessToken){
        ApiClient apiClient = new ApiClient();
        apiClient.setAccessToken(accessToken);

        CreatureApi creatureApi = new CreatureApi(apiClient);
        final CreatureApiResponse creatureApiResponse = creatureApi.getCreatureById(42722, "static-eu", "en_US");
        final ObjectMapper objectMapper = new ObjectMapper();
        try {
            final String result = objectMapper.writeValueAsString(creatureApiResponse);
            System.out.println(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
