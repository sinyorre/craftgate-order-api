package org.example.order.ports.api;


import org.example.order.model.Order;
import org.example.order.model.OrderCreate;

public interface OrderServicePort {
    Order create(OrderCreate orderCreate);
}
