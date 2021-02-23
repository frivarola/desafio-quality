package com.quality.booking.dtos.requests;

public abstract class RequestDTO {
    private String username;

    public RequestDTO() {
    }

    public RequestDTO(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
