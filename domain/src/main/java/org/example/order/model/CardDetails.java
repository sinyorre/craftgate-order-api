package org.example.order.model;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Builder
public class CardDetails {
    @NotNull
    @Size(min = 1, max = 100)
    private String holderName;

    @NotNull
    @Size(min = 16, max = 16)
    private String cardNumber;

    @NotNull
    @Size(min = 4, max = 4)
    private String expireYear;

    @NotNull
    @Size(min = 2, max = 2)
    private String expireMonth;

    @NotNull
    @Size(min = 3, max = 3)
    private String cvc;
}
