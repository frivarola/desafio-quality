package com.quality.booking.model;

public class Hotel {
    private String id;
    private String name;
    private String destination;
    private String room_type;
    private Integer price_per_night;
    private String available_since;
    private String available_to;
    private Boolean reserved;

    public Hotel(String id, String name, String destination, String room_type, Integer price_per_night, String available_since, String available_to, Boolean reserved) {
        this.id = id;
        this.name = name;
        this.destination = destination;
        this.room_type = room_type;
        this.price_per_night = price_per_night;
        this.available_since = available_since;
        this.available_to = available_to;
        this.reserved = reserved;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getRoom_type() {
        return room_type;
    }

    public void setRoom_type(String room_type) {
        this.room_type = room_type;
    }

    public Integer getPrice_per_night() {
        return price_per_night;
    }

    public void setPrice_per_night(Integer price_per_night) {
        this.price_per_night = price_per_night;
    }

    public String getAvailable_since() {
        return available_since;
    }

    public void setAvailable_since(String available_since) {
        this.available_since = available_since;
    }

    public String getAvailable_to() {
        return available_to;
    }

    public void setAvailable_to(String available_to) {
        this.available_to = available_to;
    }

    public Boolean getReserved() {
        return reserved;
    }

    public void setReserved(Boolean reserved) {
        this.reserved = reserved;
    }
}
