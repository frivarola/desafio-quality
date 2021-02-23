package com.quality.booking.dtos.requests;

import com.quality.booking.dtos.BookingDTO;

public class BookingRequestDTO extends RequestDTO {

    private BookingDTO booking;

    public BookingRequestDTO() {
        super();
    }

    public BookingRequestDTO(String username, BookingDTO booking) {
        super(username);
        this.booking = booking;
    }

    public BookingDTO getBooking() {
        return booking;
    }

    public void setBooking(BookingDTO booking) {
        this.booking = booking;
    }
}
