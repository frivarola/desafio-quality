package com.quality.booking.repository.implementations;

import com.fasterxml.jackson.core.type.TypeReference;
import com.quality.booking.dtos.FlightDTO;
import com.quality.booking.repository.interfaces.FlightRepository;
import com.quality.booking.utils.jsonEngine.JsonEngine;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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

    private static void loadDatabase() {
        TypeReference<ArrayList<FlightDTO>> tf = new TypeReference<>() {
        };
        try {
            flights = JsonEngine.readFileDB(path, tf);
        } catch (Exception e) {
            e.printStackTrace();
            flights = new ArrayList<>();
        }
    }
}
