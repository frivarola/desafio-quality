package com.quality.booking.dtos;

public class PersonDTO {
    private Long dni;
    private String name;
    private String lastname;
    private String birthDate;
    private String mail;

    public PersonDTO(Long dni, String name, String last_name, String birth_date, String mail) {
        this.dni = dni;
        this.name = name;
        this.lastname = last_name;
        this.birthDate = birth_date;
        this.mail = mail;
    }

    public Long getDni() {
        return dni;
    }

    public void setDni(Long dni) {
        this.dni = dni;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
}