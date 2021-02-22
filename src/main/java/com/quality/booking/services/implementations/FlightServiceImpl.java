package com.quality.booking.services.implementations;

import com.quality.booking.dtos.FlightDTO;
import com.quality.booking.dtos.HotelDTO;
import com.quality.booking.repository.implementations.FlightRepositoryImpl;
import com.quality.booking.services.interfaces.FlightService;
import com.quality.booking.utils.validators.DateValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<FlightDTO> getFlightsInRangeDate(String from, String to, String origin, String destination) throws ResponseStatusException {
        List<FlightDTO> flights = getAllFlightsAvailable();
        List<FlightDTO> result = new ArrayList<>();
        DateTimeFormatter formatterDB = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        //Validate date and return list of date formatted
        List<LocalDate> dateSanitized = DateValidator.validateRangeDate(from, to);
        LocalDate dateFrom = dateSanitized.get(0);
        LocalDate dateTo = dateSanitized.get(1);

        if(!existFlight(origin, destination)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El vuelo con origen " + origin + " y destino " + destination + " no existe.");
        }

        for (FlightDTO f : flights) {

            LocalDate departure_date = LocalDate.parse(f.getDeparture_date(), formatterDB);
            LocalDate return_date = LocalDate.parse(f.getReturn_date(), formatterDB);

            if (dateFrom.isAfter(departure_date) || dateFrom.isEqual(departure_date)) {
                if (dateTo.isBefore(return_date) || dateTo.isEqual(return_date)) {
                    if(f.getDestination().equals(destination)){
                        result.add(f);
                    }
                }
            }
        }

        return result;
    }

    @Override
    public Boolean reserveFlight(String id) {
        return null;
    }

    private Boolean existFlight(String origin, String destination) {
        List<FlightDTO> flights = getAllFlightsAvailable();
        List<FlightDTO> result = new ArrayList<>();
        result = flights.stream().filter(f -> f.getDestination().equals(destination) && f.getOrigin().equals(origin)).collect(Collectors.toList());

        if(result.isEmpty()) {
            return false;
        }
        return true;
    }
}
