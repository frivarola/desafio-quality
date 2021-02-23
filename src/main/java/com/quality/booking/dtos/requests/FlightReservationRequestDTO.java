package com.quality.booking.dtos.requests;

import com.quality.booking.dtos.FlightReservationDTO;

public class FlightReservationRequestDTO extends RequestDTO {
    private FlightReservationDTO flightReservation;

    public FlightReservationRequestDTO() {
        super();
    }

    public FlightReservationRequestDTO(FlightReservationDTO flightReservation, String username) {
        super(username);
        this.flightReservation = flightReservation;
    }

    public FlightReservationDTO getFlightReservation() {
        return flightReservation;
    }

    public void setFlightReservation(FlightReservationDTO flightReservation) {
        this.flightReservation = flightReservation;
    }

}
