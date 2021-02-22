package com.quality.booking.services.interfaces;

import com.quality.booking.dtos.HotelDTO;

import java.time.LocalDate;
import java.util.List;

/**
 * interface for hotel service
 * @author frivarola
 */
public interface HotelService {
    List<HotelDTO> getAllHotelsAvailable();
    List<HotelDTO> getHotelInRangeDateAndDestination(String Sfrom, String Sto, String destination);
    Boolean reserveHotel(String id);
    Boolean existDestination(String destination);
}
