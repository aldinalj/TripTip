package com.aldinalj.triptip.trip.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class TripDTO {

        @NotBlank(message = "Trip name is required.")
        @Size(min = 1, max = 25, message = "Trip name must be between 1-25 characters long.")
        @JsonProperty("name")
        private String tripName;

        @Size(max = 40, message = "Country name cannot be more than 40 characters.")
        private String country;

        @JsonProperty("start_date")
        private LocalDate startDate;

        @JsonProperty("end_date")
        private LocalDate endDate;

        public TripDTO() {}

        public TripDTO(String tripName, String country, LocalDate startDate, LocalDate endDate) {
                this.tripName = tripName;
                this.country = country;
                this.startDate = startDate;
                this.endDate = endDate;
        }

        public String getTripName() {
                return tripName;
        }

        public void setTripName(String tripName) {
                this.tripName = tripName;
        }

        public String getCountry() {
                return country;
        }

        public void setCountry(String country) {
                this.country = country;
        }

        public LocalDate getStartDate() {
                return startDate;
        }

        public void setStartDate(LocalDate startDate) {
                this.startDate = startDate;
        }

        public LocalDate getEndDate() {
                return endDate;
        }

        public void setEndDate(LocalDate endDate) {
                this.endDate = endDate;
        }
}