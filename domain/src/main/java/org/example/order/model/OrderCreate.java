package org.example.order.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class OrderCreate {
    private Long id;
    @NotNull
    private Long restaurantId;
    private String note;
    private String address;
    private BigDecimal price;
    private String referenceCode;
    @Size(min = 1)
    private List<OrderItem> orderItems;
    private OrderStatus orderStatus;
    @NotNull
    private CardDetails cardDetails;
}
