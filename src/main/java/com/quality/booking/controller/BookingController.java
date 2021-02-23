package com.quality.booking.controller;

import com.quality.booking.dtos.requests.FlightReservationRequestDTO;
import com.quality.booking.dtos.responses.FlightReservationResponseDTO;
import com.quality.booking.services.implementations.BookingServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1")
@RestController
public class BookingController {
    private BookingServiceImpl bookingService;

    public BookingController(BookingServiceImpl bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping("/flight-reservation")
    public FlightReservationResponseDTO flightReservation(@RequestBody FlightReservationRequestDTO request){
        return bookingService.reservationFlight(request);
    }

}
