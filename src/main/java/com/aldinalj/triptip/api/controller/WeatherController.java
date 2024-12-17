package com.aldinalj.triptip.api.controller;

import com.aldinalj.triptip.api.model.dto.WeatherDTO;
import com.aldinalj.triptip.api.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/current")
    public Mono<WeatherDTO> getCurrentWeather(@RequestParam String city) {

        return weatherService.fetchCurrentWeather(city);
    }
}
