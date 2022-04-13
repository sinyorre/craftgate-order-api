package org.example.configuration;

import lombok.RequiredArgsConstructor;
import org.example.order.ports.api.OrderServicePort;
import org.example.order.ports.spi.OrderSpiPort;
import org.example.order.service.OrderService;
import org.example.payment.ports.spi.PaymentSpiPort;
import org.example.restaurant.ports.spi.RestaurantSpiPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class OrderConfiguration {

    private final RestaurantSpiPort restaurantSpiPort;
    private final PaymentSpiPort paymentSpiPort;
    private final OrderSpiPort orderSpiPort;

    @Bean
    public OrderServicePort orderServicePort() {
        return new OrderService(orderSpiPort, restaurantSpiPort, paymentSpiPort);
    }
}
