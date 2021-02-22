package com.quality.booking.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

public class HotelAPIException extends ResponseStatusException {

    public HotelAPIException(HttpStatus status, String reason) {
        super(status, reason);
    }

    @Override
    public HttpStatus getStatus() {
        return super.getStatus();
    }

    @Override
    public int getRawStatusCode() {
        return super.getRawStatusCode();
    }

    @Override
    public Map<String, String> getHeaders() {
        return super.getHeaders();
    }

    @Override
    public HttpHeaders getResponseHeaders() {
        return super.getResponseHeaders();
    }

    @Override
    public String getReason() {
        return super.getReason();
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
