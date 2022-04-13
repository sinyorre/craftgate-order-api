package org.example.order.service.adapters;

import org.example.order.model.OrderCreate;
import org.example.payment.ports.spi.PaymentSpiPort;

public class PaymentFakeAdapter implements PaymentSpiPort {
    @Override
    public void pay(OrderCreate orderCreate) {
    }
}
