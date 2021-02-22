package com.quality.booking.services.implementations;

import com.quality.booking.dtos.FlightDTO;
import com.quality.booking.repository.implementations.FlightRepositoryImpl;
import com.quality.booking.services.interfaces.FlightService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

/**
 * this class implement FlightService
 * @author frivarola
 */
@Service
public class FlightServiceImpl implements FlightService {
    private FlightRepositoryImpl flightDb;

    public FlightServiceImpl(FlightRepositoryImpl flightDb) {
        this.flightDb = flightDb;
    }

    @Override
    public List<FlightDTO> getAllFlightsAvailable() {
        return flightDb.getAllFlightsAvailable();
    }

    @Override
    public List<FlightDTO> getFlightsInRangeDate(LocalDate from, LocalDate to, String origin, String destination) {
        //LLAMAR VALIDACIONES
        return flightDb.getFlightsInRangeDate(from, to, origin, destination);
    }

    @Override
    public Boolean reserveFlight(String id) {
        return flightDb.reserveFlight(id);
    }
}
