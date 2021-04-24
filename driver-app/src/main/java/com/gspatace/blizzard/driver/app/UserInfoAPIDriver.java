package com.gspatace.blizzard.driver.app;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gspatace.blizzard.oauth2.ApiClient;
import com.gspatace.blizzard.oauth2.api.UserApi;
import com.gspatace.blizzard.oauth2.model.UserInfoResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserInfoAPIDriver {
    public void doTest(String accessToken){
        final ApiClient apiClient = new ApiClient();
        apiClient.setAccessToken(accessToken);

        final UserApi userApi = new UserApi(apiClient);
        final UserInfoResponse userInfo = userApi.getUserInfo();
        final ObjectMapper objectMapper = new ObjectMapper();
        try {
            final String response = objectMapper.writeValueAsString(userInfo);
            log.info("User Info API Response: {}", response);
        } catch (JsonProcessingException e) {
            log.error("Error occurred:", e);
        }
    }
}
