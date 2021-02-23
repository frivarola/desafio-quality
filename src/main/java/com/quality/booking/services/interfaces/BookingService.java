package com.quality.booking.services.interfaces;

import com.quality.booking.dtos.requests.FlightReservationRequestDTO;
import com.quality.booking.dtos.responses.FlightReservationResponseDTO;
import org.springframework.web.server.ResponseStatusException;

public interface BookingService {
    FlightReservationResponseDTO reservationFlight(FlightReservationRequestDTO reservation) throws ResponseStatusException;

}
