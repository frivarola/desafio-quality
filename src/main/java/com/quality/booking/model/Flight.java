package com.quality.booking.model;

public class Flight {
    private String id;
    private String origin;
    private String destination;
    private String seat_type;
    private String price_per_person;
    private String departure_date;
    private String return_date;

    public Flight(String id, String origin, String destination, String seat_type, String price_per_person, String departure_date, String return_date) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.seat_type = seat_type;
        this.price_per_person = price_per_person;
        this.departure_date = departure_date;
        this.return_date = return_date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getSeat_type() {
        return seat_type;
    }

    public void setSeat_type(String seat_type) {
        this.seat_type = seat_type;
    }

    public String getPrice_per_person() {
        return price_per_person;
    }

    public void setPrice_per_person(String price_per_person) {
        this.price_per_person = price_per_person;
    }

    public String getDeparture_date() {
        return departure_date;
    }

    public void setDeparture_date(String departure_date) {
        this.departure_date = departure_date;
    }

    public String getReturn_date() {
        return return_date;
    }

    public void setReturn_date(String return_date) {
        this.return_date = return_date;
    }
}