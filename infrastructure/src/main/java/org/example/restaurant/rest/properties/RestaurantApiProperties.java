package org.example.restaurant.rest.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "adapters.restaurant")
public class RestaurantApiProperties {
    private String baseUrl;
    private String paymentPath;
    private String menuItemsPath;
}
