package com.quality.booking.repository.implementations;

import com.fasterxml.jackson.core.type.TypeReference;
import com.quality.booking.dtos.BookingDTO;
import com.quality.booking.dtos.FlightDTO;
import com.quality.booking.dtos.FlightReservationDTO;
import com.quality.booking.exceptions.JsonEngineException;
import com.quality.booking.repository.interfaces.BookingRepository;
import com.quality.booking.utils.jsonEngine.JsonEngine;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * this class is support for write flight reservation and booking on json file
 * @author frivarola
 */
@Repository
public class BookingRepositoryImpl implements BookingRepository {
    private static String reservationFlightsPath = "src/main/resources/db/reservation_flights.json";
    private static String bookingPath = "src/main/resources/db/booking.json";

    /**
     * This method is register of reservation on json file
     * @param reservation reservation flight
     * @throws JsonEngineException
     */
    @Override
    public void reservationFlight(FlightReservationDTO reservation) throws JsonEngineException{

        try {
            JsonEngine.appendDatabase(reservationFlightsPath, reservation);
        } catch (JsonEngineException e) {
            e.printStackTrace();
            throw new JsonEngineException("No se pudo instanciar los datos de la reserva.");
        }
    }

    /**
     * This method is for register booking hotel room
     * @param booking a room
     * @throws JsonEngineException
     */
    @Override
    public void booking(BookingDTO booking) throws JsonEngineException {
        try {
            JsonEngine.appendDatabase(bookingPath, booking);
        } catch (JsonEngineException e) {
            e.printStackTrace();
            throw new JsonEngineException("No se pudo instanciar los datos de la reserva.");
        }
    }

}
