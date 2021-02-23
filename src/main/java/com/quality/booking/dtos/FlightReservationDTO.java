package com.quality.booking.dtos;

import java.util.List;

public class FlightReservationDTO {
    private String flightNumber;
    private String origin;
    private String destination;
    private String seatType;
    private Integer seats;
    private String dateFrom;
    private String dateTo;
    private List<PersonDTO> people;
    private CardDTO paymentMethod;

    public FlightReservationDTO(){
    }

    public FlightReservationDTO(String flightNumber, String origin, String destination, String seatType, Integer seats, String dateFrom, String dateTo, List<PersonDTO> people, CardDTO paymentMethod) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.seatType = seatType;
        this.seats = seats;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.people = people;
        this.paymentMethod = paymentMethod;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public Integer getSeats() {
        return seats;
    }

    public void setSeats(Integer seats) {
        this.seats = seats;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public List<PersonDTO> getPeople() {
        return people;
    }

    public void setPeople(List<PersonDTO> people) {
        this.people = people;
    }

    public CardDTO getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(CardDTO paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
