package org.example.restaurant.rest;

import lombok.RequiredArgsConstructor;
import org.example.restaurant.rest.properties.RestaurantApiProperties;
import org.example.restaurant.model.MenuItem;
import org.example.restaurant.ports.spi.RestaurantSpiPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.springframework.http.HttpMethod.GET;

@Service
@RequiredArgsConstructor
public class RestaurantRestAdapter implements RestaurantSpiPort {

    private final RestTemplate restTemplate;
    private final RestaurantApiProperties restaurantApiProperties;

    @Override
    public List<MenuItem> retrieveMenuItems(List<Long> orderItemIds) {
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<MenuItem> requestEntity = new HttpEntity<>(null, headers);
        ResponseEntity<MenuItem[]> response = restTemplate.exchange(prepareMenuItemsUrl(), GET, requestEntity, MenuItem[].class);
        return Arrays.asList(Objects.requireNonNull(response.getBody()));
    }

    @Override
    public MenuItem retrieveMenuItem(Long menuItemId) {
        String url = prepareMenuItemsUrl() + "/" + menuItemId;
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<MenuItem> requestEntity = new HttpEntity<>(null, headers);
        ResponseEntity<MenuItem> response = restTemplate.exchange(url, GET, requestEntity, MenuItem.class);
        return response.getBody();
    }

    private String prepareMenuItemsUrl() {
        return restaurantApiProperties.getBaseUrl() + restaurantApiProperties.getMenuItemsPath();
    }
}
