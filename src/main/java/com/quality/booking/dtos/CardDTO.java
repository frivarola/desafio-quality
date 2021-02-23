package com.quality.booking.dtos;

public class CardDTO {
    private String type;
    private String number;
    private Integer dues;

    public CardDTO() {
    }

    public CardDTO(String type, String number, Integer dues) {
        this.type = type;
        this.number = number;
        this.dues = dues;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getDues() {
        return dues;
    }

    public void setDues(Integer dues) {
        this.dues = dues;
    }
}
