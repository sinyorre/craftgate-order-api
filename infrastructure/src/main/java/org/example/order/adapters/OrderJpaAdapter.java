package org.example.order.adapters;

import lombok.RequiredArgsConstructor;
import org.example.order.adapters.jpa.OrderJpaRepository;
import org.example.order.adapters.jpa.entity.OrderEntity;
import org.example.order.adapters.jpa.entity.OrderItemEntity;
import org.example.order.model.Order;
import org.example.order.model.OrderCreate;
import org.example.order.ports.spi.OrderSpiPort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderJpaAdapter implements OrderSpiPort {

    private final OrderJpaRepository orderJpaRepository;

    @Override
    public Order save(OrderCreate orderCreate) {
        OrderEntity orderEntity = OrderEntity.builder()
                .address(orderCreate.getAddress())
                .note(orderCreate.getNote())
                .price(orderCreate.getPrice())
                .referenceCode(orderCreate.getReferenceCode())
                .status(orderCreate.getOrderStatus())
                .build();
        List<OrderItemEntity> orderItemEntities = createOrderItemEntities(orderEntity, orderCreate);
        orderEntity.setOrderItemEntities(orderItemEntities);


        OrderEntity savedOrderEntity = orderJpaRepository.save(orderEntity);

        return toModel(savedOrderEntity);
    }

    private List<OrderItemEntity> createOrderItemEntities(OrderEntity orderEntity, OrderCreate orderCreate) {
        return orderCreate.getOrderItems().stream().map(orderItem -> {
            OrderItemEntity orderItemEntity = new OrderItemEntity();
            orderItemEntity.setOrderItemId(orderItem.getId());
            orderItemEntity.setPrice(orderItem.getPrice());
            orderItemEntity.setOrderEntity(orderEntity);

            return orderItemEntity;
        }).collect(Collectors.toList());
    }

    private Order toModel(OrderEntity orderEntity) {
        return Order.builder()
                .id(orderEntity.getId())
                .price(orderEntity.getPrice())
                .createdDate(orderEntity.getCreatedAt())
                .build();
    }
}
