package org.example.restaurant.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuItem {
    private Long id;
    private Long menuId;
    private String name;
    private String description;
    private BigDecimal price;
    private Status status;
}
