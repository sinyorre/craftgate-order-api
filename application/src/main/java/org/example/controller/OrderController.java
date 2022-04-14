package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.order.model.OrderCreate;
import org.example.order.ports.api.OrderServicePort;
import org.example.order.model.Order;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class OrderController {
    private final OrderServicePort orderServicePort;

    @PostMapping("/place-order")
    private Order placeOrder(@Valid @RequestBody OrderCreate orderCreate) {
        return orderServicePort.create(orderCreate);
    }
}
