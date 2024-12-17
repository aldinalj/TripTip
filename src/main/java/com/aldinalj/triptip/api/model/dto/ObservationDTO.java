package com.aldinalj.triptip.api.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ObservationDTO(

        @JsonProperty("sunrise")
        String sunrise,

        @JsonProperty("sunset")
        String sunset,

        @JsonProperty("timezone")
        String timezone,

        @JsonProperty("city_name")
        String cityName,

        @JsonProperty("country_code")
        String countryCode,

        @JsonProperty("wind_spd")
        double windSpeed,

        @JsonProperty("gust")
        double windGust,

        @JsonProperty("wind_dir")
        double windDirection,

        @JsonProperty("temp")
        double temperature,

        @JsonProperty("app_temp")
        double apparentTemperature,

        @JsonProperty("rh")
        double relativeHumidity,

        @JsonProperty("clouds")
        double cloudCoverage,

        @JsonProperty("pod")
        String partOfDay,

        @JsonProperty("weather")
        WeatherConditionDTO weather,

        @JsonProperty("vis")
        double visibility,

        @JsonProperty("snow")
        double snowfall,

        @JsonProperty("uv")
        double uvIndex
) {
}
