package com.quality.booking.services.implementations;

import com.quality.booking.dtos.HotelDTO;
import com.quality.booking.model.Hotel;
import com.quality.booking.repository.implementations.HotelRepositoryImpl;
import com.quality.booking.repository.interfaces.HotelRepository;
import com.quality.booking.services.interfaces.HotelService;
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
    public List<HotelDTO> getHotelInRangeDateAndDestination(String Sfrom, String Sto, String destination) {
        //LLAMAR VALIDATIONS
        DateTimeFormatter formatterRequestParam = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatterDB = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate from = LocalDate.parse(Sfrom, formatterRequestParam);
        LocalDate to = LocalDate.parse(Sto, formatterRequestParam);
        List<HotelDTO> hotels = getAllHotelsAvailable();
        List<HotelDTO> queryResult = new ArrayList<>();
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
}
