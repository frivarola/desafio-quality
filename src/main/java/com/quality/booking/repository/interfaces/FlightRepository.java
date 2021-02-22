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
    List<FlightDTO> getFlightsInRangeDate(LocalDate from, LocalDate to, String origin, String destination);
    Boolean reserveFlight(String id);

}
