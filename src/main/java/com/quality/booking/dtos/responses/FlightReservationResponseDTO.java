package com.quality.booking.dtos.responses;

import com.quality.booking.dtos.StatusDTO;
import com.quality.booking.dtos.requests.FlightReservationRequestDTO;

public class FlightReservationResponseDTO {
    private FlightReservationRequestDTO requestReservation;
    private StatusDTO statusCode;
    private Double amount;
    private Double interest;
    private Double total;

    public FlightReservationResponseDTO() {
    }

    public FlightReservationResponseDTO(FlightReservationRequestDTO requestReservation, StatusDTO statusCode, Double amount, Double interest, Double total) {
        this.requestReservation = requestReservation;
        this.statusCode = statusCode;
        this.amount = amount;
        this.interest = interest;
        this.total = total;
    }

    public FlightReservationRequestDTO getRequestReservation() {
        return requestReservation;
    }

    public void setRequestReservation(FlightReservationRequestDTO requestReservation) {
        this.requestReservation = requestReservation;
    }

    public StatusDTO getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(StatusDTO statusCode) {
        this.statusCode = statusCode;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getInterest() {
        return interest;
    }

    public void setInterest(Double interest) {
        this.interest = interest;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
