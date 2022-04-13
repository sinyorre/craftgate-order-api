package org.example.order.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class OrderCreate {
    private Long id;
    private Long restaurantId;
    private String note;
    private String address;
    private BigDecimal price;
    private String referenceCode;
    private List<OrderItem> orderItems;
    private OrderStatus orderStatus;
    private CardDetails cardDetails;
}
