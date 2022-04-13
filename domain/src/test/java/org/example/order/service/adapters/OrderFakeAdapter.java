package org.example.order.service.adapters;

import org.example.order.model.Order;
import org.example.order.model.OrderCreate;
import org.example.order.ports.spi.OrderSpiPort;

public class OrderFakeAdapter implements OrderSpiPort {
    @Override
    public Order save(OrderCreate orderCreate) {
        return Order.builder().build();
    }
}
