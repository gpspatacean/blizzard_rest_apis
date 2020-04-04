package com.gspatace.blizzard.driver.app;
import com.gspatace.blizzard.auctionhouse.ApiClient;
import com.gspatace.blizzard.auctionhouse.api.AuctionHouseApi;
import com.gspatace.blizzard.auctionhouse.model.AuctionsApiResponse;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;

/**
 * Created by George on 4/1/2020.
 */
@SpringBootApplication
public class App implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    @ConfigurationProperties("blizz-api-config.oauth2.client")
    protected ClientCredentialsResourceDetails getClientCredentialsDetails() {
        return new ClientCredentialsResourceDetails();
    }

    @Bean
    protected OAuth2RestTemplate oAuthRestTemplate() {
        return new OAuth2RestTemplate(getClientCredentialsDetails());
    }

    @Override
    public void run(String... args) {
        final ApiClient apiClient = new ApiClient();
        apiClient.setBasePath("https://us.api.blizzard.com");
        apiClient.setAccessToken(oAuthRestTemplate().getAccessToken().getValue());

        final AuctionHouseApi api = new AuctionHouseApi(apiClient);
        try {
            final AuctionsApiResponse auctionsApiResponse =
                    api.getAuctionsByConnectedRealmId(1138, "dynamic-us", "en_US");
            int noOfAuctions = auctionsApiResponse.getAuctions().size();
            System.out.println(noOfAuctions);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
