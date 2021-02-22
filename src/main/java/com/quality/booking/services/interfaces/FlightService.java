package com.quality.booking.services.interfaces;

import com.quality.booking.dtos.FlightDTO;

import java.time.LocalDate;
import java.util.List;

/**
 * interface for flight service
 * @author frivarola
 */
public interface FlightService {
    List<FlightDTO> getAllFlightsAvailable();
    List<FlightDTO> getFlightsInRangeDate(LocalDate from, LocalDate to, String origin, String destination);
    Boolean reserveFlight(String id);
}
