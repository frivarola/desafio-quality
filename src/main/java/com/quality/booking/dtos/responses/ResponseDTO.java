package com.quality.booking.dtos.responses;

import com.quality.booking.dtos.StatusDTO;
import com.quality.booking.dtos.requests.FlightReservationRequestDTO;
import com.quality.booking.dtos.requests.RequestDTO;

public class ResponseDTO {
    private RequestDTO request;
    private StatusDTO statusCode;
    private Double amount;
    private Double interest;
    private Double total;

    public ResponseDTO() {
    }

    public ResponseDTO(RequestDTO request, StatusDTO statusCode, Double amount, Double interest, Double total) {
        this.request = request;
        this.statusCode = statusCode;
        this.amount = amount;
        this.interest = interest;
        this.total = total;
    }

    public RequestDTO getRequest() {
        return request;
    }

    public void setRequest(RequestDTO request) {
        this.request = request;
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
