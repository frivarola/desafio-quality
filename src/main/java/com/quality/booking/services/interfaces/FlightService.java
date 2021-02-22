package com.quality.booking.services.interfaces;

import com.quality.booking.dtos.FlightDTO;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;

/**
 * interface for flight service
 * @author frivarola
 */
public interface FlightService {
    List<FlightDTO> getAllFlightsAvailable();
    List<FlightDTO> getFlightsInRangeDate(String from, String to, String origin, String destination) throws ResponseStatusException;
    Boolean reserveFlight(String id);
}
