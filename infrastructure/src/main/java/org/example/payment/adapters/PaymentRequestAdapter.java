package org.example.payment.adapters;

import io.craftgate.Craftgate;
import io.craftgate.exception.CraftgateException;
import io.craftgate.model.Currency;
import io.craftgate.model.PaymentGroup;
import io.craftgate.model.PaymentPhase;
import io.craftgate.model.PaymentStatus;
import io.craftgate.request.CreatePaymentRequest;
import io.craftgate.request.dto.Card;
import io.craftgate.request.dto.PaymentItem;
import io.craftgate.response.PaymentResponse;
import lombok.RequiredArgsConstructor;
import org.example.common.OrderException;
import org.example.order.model.CardDetails;
import org.example.order.model.OrderCreate;
import org.example.order.model.OrderItem;
import org.example.payment.ports.spi.PaymentSpiPort;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentRequestAdapter implements PaymentSpiPort {

    private final Craftgate craftgate;

    @Override
    public void pay(OrderCreate orderCreate) {
        List<OrderItem> orderItems = orderCreate.getOrderItems();
        if (orderItems == null) throw new OrderException("order items cannot be null");
        List<PaymentItem> items = toPaymentItems(orderItems);
        CreatePaymentRequest paymentRequest = createPaymentRequest(orderCreate, items);
        PaymentResponse paymentResponse = craftgate.payment().createPayment(paymentRequest);
    }

    private CreatePaymentRequest createPaymentRequest(OrderCreate orderCreate, List<PaymentItem> items) {
        CardDetails cardDetails = orderCreate.getCardDetails();
        return CreatePaymentRequest.builder()
                .price(orderCreate.getPrice())
                .paidPrice(orderCreate.getPrice())
                .walletPrice(BigDecimal.ZERO)
                .installment(1)
                .currency(Currency.TRY)
                .conversationId("456d1297-908e-4bd6-a13b-4be31a6e47d5")
                .paymentGroup(PaymentGroup.LISTING_OR_SUBSCRIPTION)
                .paymentPhase(PaymentPhase.AUTH)
                .card(Card.builder()
                        .cardHolderName(cardDetails.getHolderName())
                        .cardNumber(cardDetails.getCardNumber())
                        .expireYear(cardDetails.getExpireYear())
                        .expireMonth(cardDetails.getExpireMonth())
                        .cvc(cardDetails.getCvc())
                        .build())
                .items(items)
                .build();
    }

    private List<PaymentItem> toPaymentItems(List<OrderItem> orderItems) {
        List<PaymentItem> items = new ArrayList<>();
        orderItems.forEach(orderItem -> {
            items.add(PaymentItem.builder()
                    .name(orderItem.getName())
                    .externalId(UUID.randomUUID().toString())
                    .price(orderItem.getPrice())
                    .build());
        });
        return items;
    }
}
