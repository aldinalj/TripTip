package com.aldinalj.triptip.api.service;

import com.aldinalj.triptip.api.model.dto.CurrencyDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class CurrencyService {

    @Value("${currency.api.key}")
    private String apiKey;

    private final WebClient currencyWebClient;

    @Autowired
    public CurrencyService(@Qualifier(value = "currencyWebClient") WebClient currencyWebClient) {
        this.currencyWebClient = currencyWebClient;
    }

    public Mono<CurrencyDTO> fetchCurrency(String from, String to, Double amount) {


            String uri = String.format("/%s/pair/%s/%s/%s", apiKey, from, to, amount);

            return currencyWebClient.get()
                    .uri(uri)
                    .retrieve()
                    .bodyToMono(CurrencyDTO.class);
    }

}
