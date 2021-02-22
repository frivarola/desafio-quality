package com.quality.booking.utils.validators;

import com.quality.booking.exceptions.HotelAPIException;
import com.quality.booking.model.Hotel;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * this class validate hotel API request
 * @author frivarola
 */
public class HotelValidator {

    /**
     * This method validate date and return date formatted
     * @param Sfrom from date
     * @param Sto to date
     * @return dates formatted and validated
     * @throws HotelAPIException
     */
    public static List<LocalDate>  validateRangeDate(String Sfrom, String Sto) throws HotelAPIException {
        List<LocalDate> dateFormatted = new ArrayList<>();

        DateTimeFormatter formatterRequestParam = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try{

            LocalDate from = LocalDate.parse(Sfrom, formatterRequestParam);
            LocalDate to = LocalDate.parse(Sto, formatterRequestParam);

            if(from.isAfter(to)){
                throw new HotelAPIException(HttpStatus.BAD_REQUEST, "La fecha de entrada debe ser menor a la de salida");
            }
            if(to.isBefore(from)){
                throw new HotelAPIException(HttpStatus.BAD_REQUEST, "La fecha de salida debe ser mayor a la de entrada");
            }
            dateFormatted.add(from);
            dateFormatted.add(to);

            return dateFormatted;
        } catch (DateTimeParseException de){
            de.printStackTrace();
            throw new HotelAPIException(HttpStatus.BAD_REQUEST, "Formato de fecha debe ser dd/mm/aaaa");
        }

    }
}
