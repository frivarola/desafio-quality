package com.quality.booking.dtos.requests;

import com.quality.booking.dtos.FlightReservationDTO;

public class FlightReservationRequestDTO {
    private FlightReservationDTO flightReservation;
    private String username;

    public FlightReservationRequestDTO() {
    }

    public FlightReservationRequestDTO(FlightReservationDTO flightReservation, String username) {
        this.flightReservation = flightReservation;
        this.username = username;
    }

    public FlightReservationDTO getFlightReservation() {
        return flightReservation;
    }

    public void setFlightReservation(FlightReservationDTO flightReservation) {
        this.flightReservation = flightReservation;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
