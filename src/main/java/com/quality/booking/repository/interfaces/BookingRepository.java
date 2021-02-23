package com.quality.booking.repository.interfaces;

import com.quality.booking.dtos.BookingDTO;
import com.quality.booking.dtos.FlightReservationDTO;
import com.quality.booking.exceptions.JsonEngineException;

/**
 * interface for booking repository
 */
public interface BookingRepository {

    void reservationFlight(FlightReservationDTO reservation) throws JsonEngineException;
    void booking(BookingDTO booking) throws JsonEngineException;

}
