package org.example.payment.ports.spi;

import org.example.order.model.OrderCreate;

public interface PaymentSpiPort {
    void pay(OrderCreate orderCreate);
}
