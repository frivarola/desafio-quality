package com.quality.booking.utils.validators;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * this class validate date format
 * @author frivarola
 */
public class DateValidator {

    /**
     * This method validate range date and return date formatted
     * @param Sfrom from date
     * @param Sto to date
     * @return dates formatted and validated
     * @throws ResponseStatusException
     */
    public static List<LocalDate>  validateRangeDate(String Sfrom, String Sto) throws ResponseStatusException {
        List<LocalDate> dateFormatted = new ArrayList<>();

        DateTimeFormatter formatterRequestParam = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try{

            LocalDate from = LocalDate.parse(Sfrom, formatterRequestParam);
            LocalDate to = LocalDate.parse(Sto, formatterRequestParam);

            if(from.isAfter(to)){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La fecha de entrada debe ser menor a la de salida");
            }
            if(to.isBefore(from)){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La fecha de salida debe ser mayor a la de entrada");
            }
            dateFormatted.add(from);
            dateFormatted.add(to);

            return dateFormatted;
        } catch (DateTimeParseException de){
            de.printStackTrace();
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Formato de fecha debe ser dd/mm/aaaa");
        }

    }
}
