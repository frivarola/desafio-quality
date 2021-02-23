package com.quality.booking.repository.interfaces;

import com.quality.booking.dtos.BookingDTO;
import com.quality.booking.dtos.FlightReservationDTO;

/**
 * interface for booking repository
 */
public interface BookingRepository {

    void reservationFlight(FlightReservationDTO reservation) throws Exception;
    void booking(BookingDTO booking) throws Exception;

}
