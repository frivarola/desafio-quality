package com.quality.booking.repository.implementations;

import com.fasterxml.jackson.core.type.TypeReference;
import com.quality.booking.dtos.FlightDTO;
import com.quality.booking.exceptions.JsonEngineException;
import com.quality.booking.repository.interfaces.FlightRepository;
import com.quality.booking.utils.jsonEngine.JsonEngine;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * this class implement FlightRepository and this simulate a db connector.
 * @author frivarola
 */
@Repository
public class FlightRepositoryImpl implements FlightRepository {
    private static List<FlightDTO> flights = null;
    private static String path = "src/main/resources/db/fligths.json";

    static {
        loadDatabase();
    }

    /**
     * Return all flights availables on database
     *
     * @return all flights
     */
    @Override
    public List<FlightDTO> getAllFlightsAvailable() {
        return flights;
    }

    /**
     * Return all flights in range date where result match with origin and destination.
     *
     * @param from
     * @param to
     * @param origin
     * @param destination
     * @return flights matched
     */
    @Override
    public List<FlightDTO> getFlightsInRangeDate(LocalDate from, LocalDate to, String origin, String destination) {
        List<FlightDTO> queryResult = new ArrayList<>();
        for (FlightDTO f : flights) {
            LocalDate f_departure_date = LocalDate.parse(f.getDeparture_date());

            //this logic simulate a query
            if (from.isAfter(f_departure_date) || from.isEqual(f_departure_date)) {

                if (to.isBefore(f_departure_date) || to.isEqual(f_departure_date)) {

                    if (f.getOrigin().equals(origin) && f.getDestination().equals(destination)) {
                        queryResult.add(f);
                    }
                }

            }
        }
        return queryResult;
    }

    @Override
    public Boolean reserveFlight(String id) {
        return null;
    }

    private static void loadDatabase() {
        TypeReference<ArrayList<FlightDTO>> tf = new TypeReference<>() {
        };
        try {
            flights = JsonEngine.readFileDB(path, tf);
        } catch (JsonEngineException e) {
            e.printStackTrace();
            flights = new ArrayList<>();
        }
    }
}
