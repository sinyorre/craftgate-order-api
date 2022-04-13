package org.example.order.service;

import lombok.RequiredArgsConstructor;
import org.example.order.model.Order;
import org.example.order.model.OrderCreate;
import org.example.order.model.OrderItem;
import org.example.order.ports.api.OrderServicePort;
import org.example.order.ports.spi.OrderSpiPort;
import org.example.payment.ports.spi.PaymentSpiPort;
import org.example.restaurant.model.MenuItem;
import org.example.restaurant.model.Status;
import org.example.restaurant.ports.spi.RestaurantSpiPort;

import java.math.BigDecimal;

@RequiredArgsConstructor
public class OrderService implements OrderServicePort {

    private final OrderSpiPort orderSpiPort;
    private final RestaurantSpiPort restaurantSpiPort;
    private final PaymentSpiPort paymentSpiPort;

    @Override
    public Order create(OrderCreate orderCreate) {
        validate(orderCreate);
        save(orderCreate);
        pay(orderCreate);
        return save(orderCreate);
    }

    void validate(OrderCreate orderCreate) {
        BigDecimal orderPrice = orderCreate.getOrderItems().stream().map(OrderItem::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
        if (!orderCreate.getPrice().equals(orderPrice)) {
            throw new RuntimeException("order price not equal to sum of price");
        }
        orderCreate.getOrderItems().forEach(orderItem -> {
            MenuItem menuItem = restaurantSpiPort.retrieveMenuItem(orderItem.getId());
            if (menuItem.getPrice().compareTo(orderItem.getPrice()) != 0) {
                throw new RuntimeException("menu item price not equal to item price");
            }
            if (menuItem.getStatus() != Status.ACTIVE) {
                throw new RuntimeException("menu item is not active");
            }
        });
    }

    void pay(OrderCreate orderCreate) {
        paymentSpiPort.pay(orderCreate);
    }

    private Order save(OrderCreate orderCreate) {
        Order order = orderSpiPort.save(orderCreate);
        orderCreate.setId(order.getId());
        return order;
    }
}
