package org.example.integration;

import org.example.IT;
import org.example.order.adapters.OrderJpaAdapter;
import org.example.order.model.Order;
import org.example.order.model.OrderCreate;
import org.example.order.model.OrderItem;
import org.example.order.model.OrderStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@IT
public class OrderJpaIT {

    @Autowired
    OrderJpaAdapter orderJpaAdapter;

    @Test
    public void should_save_order() {
        OrderItem orderItem = OrderItem.builder()
                .id(1L)
                .price(BigDecimal.TEN)
                .name("TEST_ITEM")
                .build();
        OrderCreate orderCreate = OrderCreate.builder()
                .address("TEST_ADDRESS")
                .note("TEST_NOTE")
                .price(BigDecimal.TEN)
                .referenceCode("TEST_REF_CODE")
                .orderStatus(OrderStatus.RECEIVED)
                .orderItems(List.of(orderItem))
                .build();
        Order order = orderJpaAdapter.save(orderCreate);
        assertThat(order.getPrice()).isEqualTo(BigDecimal.TEN);
    }
}
