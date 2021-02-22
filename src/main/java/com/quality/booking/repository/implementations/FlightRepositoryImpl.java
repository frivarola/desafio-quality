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
     * This method save the flights on json file.
     * @return false in case an error occurred
     */
    @Override
    public Boolean setAllFlights(List<FlightDTO> update) {
        if (update != null) {
            this.flights = update;

            try {

                JsonEngine.writeDatabase(path, this.flights);

            } catch (JsonEngineException e) {
                e.printStackTrace();
                return false;
            }
        }

        return true;
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
