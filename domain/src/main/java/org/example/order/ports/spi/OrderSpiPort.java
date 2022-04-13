package org.example.order.ports.spi;


import org.example.order.model.Order;
import org.example.order.model.OrderCreate;

public interface OrderSpiPort {
    Order save(OrderCreate orderCreate);
}
