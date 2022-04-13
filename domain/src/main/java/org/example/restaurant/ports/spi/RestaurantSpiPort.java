package org.example.restaurant.ports.spi;


import org.example.restaurant.model.MenuItem;

import java.util.List;

public interface RestaurantSpiPort {
    List<MenuItem> retrieveMenuItems(List<Long> orderItemIds);

    MenuItem retrieveMenuItem(Long menuItemId);
}
