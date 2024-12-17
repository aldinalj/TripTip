package com.aldinalj.triptip.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean(name = "currencyWebClient")
    public WebClient currencyWebClient(WebClient.Builder builder) {

        return builder.baseUrl("https://www.amdoren.com/api/currency.php")
                .build();
    }

    @Bean(name = "weatherWebClient")
    public WebClient weatherWebClient(WebClient.Builder builder) {

        return builder.baseUrl("https://api.weatherbit.io/v2.0/current")
                .build();
    }
}

