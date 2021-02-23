package com.quality.booking.services.interfaces;

import com.quality.booking.dtos.requests.BookingRequestDTO;
import com.quality.booking.dtos.requests.FlightReservationRequestDTO;
import com.quality.booking.dtos.requests.RequestDTO;
import com.quality.booking.dtos.responses.FlightReservationResponseDTO;
import com.quality.booking.dtos.responses.ResponseDTO;
import org.springframework.web.server.ResponseStatusException;

/**
 * interface for booking service
 * @author frivarola
 */
public interface BookingService {
    FlightReservationResponseDTO reservationFlight(FlightReservationRequestDTO reservation) throws ResponseStatusException;
    ResponseDTO bookingHotel(BookingRequestDTO booking) throws ResponseStatusException;

}
