package com.quality.booking.services.implementations;

import com.quality.booking.dtos.*;
import com.quality.booking.dtos.requests.BookingRequestDTO;
import com.quality.booking.dtos.requests.FlightReservationRequestDTO;
import com.quality.booking.dtos.responses.FlightReservationResponseDTO;
import com.quality.booking.dtos.responses.ResponseDTO;
import com.quality.booking.exceptions.JsonEngineException;
import com.quality.booking.repository.implementations.BookingRepositoryImpl;
import com.quality.booking.services.interfaces.BookingService;
import com.quality.booking.utils.validators.BookingEngineValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * this class implement booking service
 *
 * @author frivarola
 */
@Service
public class BookingServiceImpl implements BookingService {
    private BookingRepositoryImpl repository;
    private FlightServiceImpl flightService;
    private HotelServiceImpl hotelService;

    public BookingServiceImpl(BookingRepositoryImpl repository, FlightServiceImpl flightService, HotelServiceImpl hotelService) {
        this.repository = repository;
        this.flightService = flightService;
        this.hotelService = hotelService;
    }

    /**
     * This method validate reservation request and register the same on booking repository
     *
     * @param reservation
     * @return Response
     * @throws ResponseStatusException
     */
    @Override
    public FlightReservationResponseDTO reservationFlight(FlightReservationRequestDTO reservation) throws ResponseStatusException {

        if (reservation.getFlightReservation() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Flight reservation detail is null");
        }

        List<PersonDTO> people = reservation.getFlightReservation().getPeople();
        FlightReservationDTO reservationDetail = reservation.getFlightReservation();

        //validate date
        BookingEngineValidator.validateRangeDate(reservationDetail.getDateFrom(), reservationDetail.getDateTo(), "dd/MM/yyyy");
        //validate and calculate interest
        Double interests = BookingEngineValidator.calculateInterest(reservationDetail.getPaymentMethod());

        //validate mails
        for (PersonDTO p : people) {
            BookingEngineValidator.validateEmail(p.getMail());
        }

        // GET FLIGHT BY ID FLIGHT AND SEAT TYPE
        // and validate origin and destination
        List<FlightDTO> flights = flightService.getFlightsInRangeDate(reservationDetail.getDateFrom(), reservationDetail.getDateTo(), reservationDetail.getOrigin(), reservationDetail.getDestination());
        flights = flights.stream().filter(f -> f.getId().equals(reservationDetail.getFlightNumber()) && f.getSeat_type().equals(reservationDetail.getSeatType())).collect(Collectors.toList());

        if (flights.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro el vuelo indicado.");
        }

        FlightDTO flightReserved = flights.get(0);
        //calculate total of price
        Integer amount = flightReserved.getPrice_per_person() * reservationDetail.getSeats();

        try {
            repository.reservationFlight(reservationDetail);
        } catch (JsonEngineException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocurrió un error al escribir el archivo json de la base de datos.");
        }

        return new FlightReservationResponseDTO(reservation, new StatusDTO(200, "El proceso finalizo correctamente."), amount.doubleValue(), interests, amount.doubleValue() + (amount.doubleValue() * interests));
    }

    /**
     * This method validate booking request and register the same on booking repository
     *
     * @param booking
     * @return Response
     * @throws ResponseStatusException
     */
    @Override
    public ResponseDTO bookingHotel(BookingRequestDTO booking) throws ResponseStatusException {

        if (booking.getBooking() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Booking detail is null");
        }

        List<PersonDTO> people = booking.getBooking().getPeople();
        BookingDTO bookingDetail = booking.getBooking();

        //validate date
        BookingEngineValidator.validateRangeDate(bookingDetail.getDateFrom(), bookingDetail.getDateTo(), "dd/MM/yyyy");
        //validate and calculate interest
        Double interests = BookingEngineValidator.calculateInterest(bookingDetail.getPaymentMethod());
        //validate type room
        BookingEngineValidator.validateRoom(bookingDetail.getRoomType(), bookingDetail.getPeopleAmount());

        //validate mails
        for (PersonDTO p : people) {
            BookingEngineValidator.validateEmail(p.getMail());
        }

        // GET HOTEL BY HOTEL CODE AND ROOM TYPE
        // and validate Destination
        List<HotelDTO> hotels = hotelService.getHotelInRangeDateAndDestination(bookingDetail.getDateFrom(), bookingDetail.getDateTo(), bookingDetail.getDestination());
        hotels = hotels.stream().filter(h -> h.getId().equals(bookingDetail.getHotelCode()) && h.getRoom_type().equals(bookingDetail.getRoomType()) && h.getReserved().equals(false)).collect(Collectors.toList());

        if (hotels.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No se encontro el hotel indicado.");
        }

        HotelDTO hotelBooking = hotels.get(0);
        //calculate total of price
        Integer amount = hotelBooking.getPrice_per_night() * bookingDetail.getPeopleAmount();

        try {
            repository.booking(bookingDetail);
            hotelService.reserveHotel(bookingDetail.getHotelCode());
        } catch (JsonEngineException e) {
            e.printStackTrace();
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Ocurrió un error al escribir el archivo json de la base de datos.");
        }

        return new ResponseDTO(booking, new StatusDTO(200, "El proceso se finalizo correctamente."), amount.doubleValue(), interests, amount + (amount * interests));
    }
}
