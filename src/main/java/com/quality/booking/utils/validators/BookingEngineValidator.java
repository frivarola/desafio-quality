package com.quality.booking.utils.validators;

import com.quality.booking.dtos.CardDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * this class contains utils for booking api
 * @author frivarola
 */
public class BookingEngineValidator {

    /**
     * This method validate range date and return date formatted
     * @param Sfrom from date
     * @param Sto to date
     * @return dates formatted and validated
     * @throws ResponseStatusException
     */
    public static List<LocalDate> validateRangeDate(String Sfrom, String Sto, String format) throws ResponseStatusException {
        List<LocalDate> dateFormatted = new ArrayList<>();

        DateTimeFormatter formatterRequestParam = DateTimeFormatter.ofPattern(format);
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
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Formato de fecha debe ser " + format);
        }

    }

    /**
     * this method calculate interest and validate card type
     * @param card
     * @return interest or -1 in case invalid card
     */
    public static Double calculateInterest(CardDTO card) throws ResponseStatusException{
        Double interests = 0.0;
        if(card.getType().equals("CREDIT")){
            int dues = card.getDues();
            while(dues > 0){
                interests += 0.05;
                dues -= 3;
            }

            return interests;
        }

        if(card.getType().equals("DEBIT")){
            if(card.getDues() != 1){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Se ingreso una cantidad de cuotas diferente a 1 para una tarjeta de debito");
            }
        }

        return interests;
    }

    public static Boolean validateEmail(String email) throws ResponseStatusException{
        Pattern p = Pattern.compile("^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$");
        Matcher m = p.matcher(email);

        if(m.matches()){
           return true;
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Por favor, ingrese un e-mail valido.");
        }

    }
}
