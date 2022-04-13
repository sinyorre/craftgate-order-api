package org.example.payment.ports.api;


import org.example.order.model.OrderCreate;

public interface PaymentServicePort {
    void pay(OrderCreate orderCreate);
}
