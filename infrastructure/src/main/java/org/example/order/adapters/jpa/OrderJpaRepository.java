package org.example.order.adapters.jpa;


import org.example.order.adapters.jpa.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJpaRepository extends JpaRepository<OrderEntity, Long> {
}
