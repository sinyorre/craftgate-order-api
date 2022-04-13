package org.example.integration;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.example.IT;
import org.example.restaurant.model.MenuItem;
import org.example.restaurant.model.Status;
import org.example.restaurant.rest.RestaurantRestAdapter;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;

@IT
public class RestaurantRestAdapterIT {

    private static final WireMockServer wireMockServer = new WireMockServer(8080);

    @Autowired
    RestaurantRestAdapter restaurantRestAdapter;

    @BeforeAll
    static void setup() {
        wireMockServer.start();

        wireMockServer.stubFor(
                get("/items/1")
                        .willReturn(aResponse()
                                .withBody("{\n" +
                                        "    \"name\": \"Döner\",\n" +
                                        "    \"description\": \"Döner Açıklama\",\n" +
                                        "    \"price\": 35,\n" +
                                        "    \"status\": \"ACTIVE\"\n" +
                                        "}")
                                .withHeader("Content-Type", "application/json")
                                .withStatus(HttpStatus.OK.value())
                        )
        );
    }

    @AfterAll
    static void teardown() {
        wireMockServer.stop();
    }

    @Test
    void should_call_restaurant_api(){
        Long menuItemId = 1L;
        MenuItem menuItem = restaurantRestAdapter.retrieveMenuItem(menuItemId);
        MenuItem expectedMenuItem = MenuItem.builder()
                .name("Döner")
                .description("Döner Açıklama")
                .price(BigDecimal.valueOf(35))
                .status(Status.ACTIVE).build();
        assertThat(menuItem).isEqualTo(expectedMenuItem);
    }
}
