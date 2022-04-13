package org.example.restaurant.ports.api;

import org.example.restaurant.model.MenuItem;

import java.util.List;

public interface RestaurantServicePort {
    List<MenuItem> retrieveMenuItems(List<Long> menuItemIds);
    MenuItem retrieveMenuItem(Long menuItemId);
}
