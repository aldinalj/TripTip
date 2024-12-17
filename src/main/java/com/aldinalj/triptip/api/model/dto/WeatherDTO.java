package com.aldinalj.triptip.api.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record WeatherDTO(

        @JsonProperty("data")
        List<ObservationDTO> data

) {
}
