package org.example.payment.configuration;

import io.craftgate.Craftgate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CraftgateConfiguration {
    @Bean
    public Craftgate createCraftgate() {
        return new Craftgate(
                "sandbox-msJRidRgOPyZrrVVTgIsTTJtJBntCEJq",
                "sandbox-RLoXAOQtjfjrsFYsRzyvREoWFnXmqXcL",
                "https://sandbox-api.craftgate.io"
        );
    }
}
