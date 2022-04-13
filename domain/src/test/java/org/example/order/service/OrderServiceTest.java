package org.example.order.service;

import org.example.order.model.OrderCreate;
import org.example.order.model.OrderItem;
import org.example.order.service.adapters.OrderFakeAdapter;
import org.example.order.service.adapters.PaymentFakeAdapter;
import org.example.order.service.adapters.RestaurantFakeAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OrderServiceTest {
    OrderService orderService;

    @BeforeEach
    void setUp() {
        orderService = new OrderService(new OrderFakeAdapter(), new RestaurantFakeAdapter(), new PaymentFakeAdapter());
    }

    @Test
    void should_throw_exception_when_price_incompatible() {
        OrderItem orderItemOne = OrderItem.builder()
                .price(BigDecimal.valueOf(15))
                .name("TEST_NAME")
                .id(1L)
                .build();
        OrderItem orderItemTwo = OrderItem.builder()
                .price(BigDecimal.valueOf(35))
                .name("TEST_NAME")
                .id(1L)
                .build();
        OrderCreate orderCreate = OrderCreate.builder()
                .price(BigDecimal.valueOf(5))
                .orderItems(List.of(orderItemOne, orderItemTwo))
                .note("TEST_NOTE")
                .address("TEST_ADDRESS")
                .build();

        Exception exception = assertThrows(RuntimeException.class, () -> {
            orderService.validate(orderCreate);
        });

        String expectedMessage = "order price not equal to sum of price";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void should_throw_exception_when_orderItem_and_menuItem_price_incompatible() {
        OrderItem orderItemOne = OrderItem.builder()
                .price(BigDecimal.valueOf(15))
                .name("TEST_NAME")
                .id(1L)
                .build();
        OrderItem orderItemTwo = OrderItem.builder()
                .price(BigDecimal.valueOf(35))
                .name("TEST_NAME")
                .id(1L)
                .build();
        OrderCreate orderCreate = OrderCreate.builder()
                .price(BigDecimal.valueOf(50))
                .orderItems(List.of(orderItemOne, orderItemTwo))
                .note("TEST_NOTE")
                .address("TEST_ADDRESS")
                .build();

        Exception exception = assertThrows(RuntimeException.class, () -> {
            orderService.validate(orderCreate);
        });

        String expectedMessage = "menu item price not equal to item price";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void should_throw_exception_when_status_not_active() {
        OrderItem orderItemOne = OrderItem.builder()
                .price(BigDecimal.valueOf(15))
                .name("TEST_NAME")
                .id(100L)
                .build();
        OrderCreate orderCreate = OrderCreate.builder()
                .price(BigDecimal.valueOf(15))
                .orderItems(List.of(orderItemOne))
                .note("TEST_NOTE")
                .address("TEST_ADDRESS")
                .build();

        Exception exception = assertThrows(RuntimeException.class, () -> {
            orderService.validate(orderCreate);
        });

        String expectedMessage = "menu item is not active";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}