package com.quality.booking.repository.implementations;

import com.quality.booking.dtos.BookingDTO;
import com.quality.booking.dtos.FlightReservationDTO;
import com.quality.booking.repository.interfaces.BookingRepository;
import com.quality.booking.utils.jsonEngine.JsonEngine;
import org.springframework.stereotype.Repository;

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
     * @throws Exception exception file
     */
    @Override
    public void reservationFlight(FlightReservationDTO reservation) throws Exception{

        try {
            JsonEngine.appendDatabase(reservationFlightsPath, reservation);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("No se pudo instanciar los datos de la reserva.");
        }
    }

    /**
     * This method is for register booking hotel room
     * @param booking a room
     * @throws Exception file
     */
    @Override
    public void booking(BookingDTO booking) throws Exception {
        try {
            JsonEngine.appendDatabase(bookingPath, booking);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("No se pudo instanciar los datos de la reserva.");
        }
    }

}
