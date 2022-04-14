package org.example.integration;

import org.example.IT;
import org.example.order.model.CardDetails;
import org.example.order.model.OrderCreate;
import org.example.order.model.OrderItem;
import org.example.payment.adapters.PaymentRequestAdapter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

@IT
public class PaymentRequestAdapterIT {
    @Autowired
    PaymentRequestAdapter paymentRequestAdapter;

    @Test
    public void should_pay() {
        OrderItem orderItemOne = OrderItem.builder()
                .name("Item 1")
                .price(BigDecimal.valueOf(20))
                .build();
        OrderItem orderItemTwo = OrderItem.builder()
                .name("Item 2")
                .price(BigDecimal.valueOf(15))
                .build();
        CardDetails cardDetails = CardDetails.builder()
                .holderName("Sinan Alagöz")
                .cardNumber("5258640000000001")
                .expireMonth("07")
                .expireYear("2044")
                .cvc("000")
                .build();
        OrderCreate orderCreate = OrderCreate.builder()
                .price(BigDecimal.valueOf(35))
                .orderItems(List.of(orderItemOne, orderItemTwo))
                .cardDetails(cardDetails)
                .build();
        paymentRequestAdapter.pay(orderCreate);
        Assertions.assertDoesNotThrow(() -> paymentRequestAdapter.pay(orderCreate));
    }
}
