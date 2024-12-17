package com.aldinalj.triptip.api.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record WeatherConditionDTO(

        @JsonProperty("icon")
        String icon,

        @JsonProperty("code")
        int code,

        @JsonProperty("description")
        String description

) {
}
