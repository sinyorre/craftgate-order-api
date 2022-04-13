package org.example.order.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CardDetails {
    private String holderName;
    private String cardNumber;
    private String expireYear;
    private String expireMonth;
    private String cvc;
}
