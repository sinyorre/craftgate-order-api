package org.example.order.service.adapters;

import org.example.restaurant.model.MenuItem;
import org.example.restaurant.model.Status;
import org.example.restaurant.ports.spi.RestaurantSpiPort;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class RestaurantFakeAdapter implements RestaurantSpiPort {
    private static final Long CREATE_NOT_ACTIVE_MENU_ITEM_ID = 100L;

    @Override
    public List<MenuItem> retrieveMenuItems(List<Long> orderItemIds) {
        MenuItem menuItemOne = MenuItem.builder()
                .price(BigDecimal.TEN)
                .build();
        MenuItem menuItemTwo = MenuItem.builder()
                .price(BigDecimal.valueOf(15))
                .build();
        return List.of(menuItemOne, menuItemTwo);
    }

    @Override
    public MenuItem retrieveMenuItem(Long menuItemId) {
        if (Objects.equals(menuItemId, CREATE_NOT_ACTIVE_MENU_ITEM_ID)) {
            return MenuItem.builder()
                    .id(menuItemId)
                    .price(BigDecimal.valueOf(15))
                    .status(Status.PASSIVE)
                    .build();
        }
        return MenuItem.builder()
                .id(menuItemId)
                .price(BigDecimal.TEN)
                .build();
    }
}
