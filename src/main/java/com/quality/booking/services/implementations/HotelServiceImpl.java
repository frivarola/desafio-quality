package com.quality.booking.services.implementations;

import com.quality.booking.dtos.HotelDTO;
import com.quality.booking.exceptions.HotelAPIException;
import com.quality.booking.model.Hotel;
import com.quality.booking.repository.implementations.HotelRepositoryImpl;
import com.quality.booking.repository.interfaces.HotelRepository;
import com.quality.booking.services.interfaces.HotelService;
import com.quality.booking.utils.validators.HotelValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * hotel service implementation
 * @author frivarola
 */
@Service
public class HotelServiceImpl implements HotelService {

    private HotelRepositoryImpl hotelDb;

    public HotelServiceImpl(HotelRepositoryImpl hotelRepository) {
        this.hotelDb = hotelRepository;
    }

    @Override
    public List<HotelDTO> getAllHotelsAvailable() {
        return hotelDb.getAllHotelsAvailable();
    }

    @Override
    public List<HotelDTO> getHotelInRangeDateAndDestination(String Sfrom, String Sto, String destination) throws HotelAPIException {
        List<HotelDTO> hotels = getAllHotelsAvailable();
        List<HotelDTO> queryResult = new ArrayList<>();
        DateTimeFormatter formatterDB = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        //Validate date and return list of date formatted
        List<LocalDate> dateSanitized = HotelValidator.validateRangeDate(Sfrom, Sto);
        LocalDate from = dateSanitized.get(0);
        LocalDate to = dateSanitized.get(1);

        if(!existDestination(destination)){
            throw new HotelAPIException(HttpStatus.NOT_FOUND,  "El destino elegido no existe");
        }

        for (HotelDTO h : hotels) {

            LocalDate h_available_since = LocalDate.parse(h.getAvailable_since(), formatterDB);
            LocalDate h_available_to = LocalDate.parse(h.getAvailable_to(), formatterDB);

            //this logic simulate a query SELECT * FROM HOTELS WHERE AVAILABLE_SINCE <= FROM AND AVAILABLE_TO >= TO AND DESTINATION = DESTINATION
            if (from.isAfter(h_available_since) || from.isEqual(h_available_since)) {
                if (to.isBefore(h_available_to) || to.isEqual(h_available_to)) {
                    if(h.getDestination().equals(destination)){
                        queryResult.add(h);
                    }
                }
            }
        }
        return queryResult;
    }

    @Override
    public Boolean reserveHotel(String id) {
        List<HotelDTO> hotelsQuery = getAllHotelsAvailable();
        Boolean find = false;
        int i = 0;
        //this logic simulate a "UPDATE HOTELS SET RESERVED = TRUE WHERE ID = id
        while (!find && i < hotelsQuery.size()) {
            HotelDTO h = hotelsQuery.get(i);
            if (h.getId().equals(id)) {
                if (!h.getReserved()) {
                    find = true;
                    h.setReserved(true);
                    hotelsQuery.set(i, h);
                }
            }
            i++;
        }

        return find;
    }

    @Override
    public Boolean existDestination(String destination) {
        List<HotelDTO> hotels = getAllHotelsAvailable();
        List<HotelDTO> result = new ArrayList<>();
        hotels.stream().filter(h -> h.getDestination().equals(destination)).map( h -> result.add(h));

        if(result.isEmpty()) {
            return false;
        }
        return true;
    }
}
