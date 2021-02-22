package com.quality.booking.repository.interfaces;

import com.quality.booking.dtos.FlightDTO;

import java.time.LocalDate;
import java.util.List;

/**
 * Flight Repository interface
 * @author frivarola
 */
public interface FlightRepository {
    List<FlightDTO> getAllFlightsAvailable();
    Boolean setAllFlights(List<FlightDTO> update);

}
