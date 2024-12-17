package com.aldinalj.triptip.api.service;

import com.aldinalj.triptip.api.model.dto.WeatherDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    private final WebClient weatherWebClient;

    @Autowired
    public WeatherService(@Qualifier(value = "weatherWebClient") WebClient weatherWebClient) {
        this.weatherWebClient = weatherWebClient;
    }

    public Mono<WeatherDTO> fetchCurrentWeather(@RequestParam("city") String city) {

        return weatherWebClient.get()
                .uri(weather -> weather
                        .queryParam("city", city)
                        .queryParam("key", apiKey)
                        .build()
                )
                .retrieve()
                .bodyToMono(WeatherDTO.class);
    }


}
