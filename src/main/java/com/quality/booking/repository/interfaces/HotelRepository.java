package com.quality.booking.repository.interfaces;

import com.quality.booking.dtos.HotelDTO;

import java.time.LocalDate;
import java.util.List;

/**
 * Hotel repository interface
 * @author frivarola
 */
public interface HotelRepository {
    List<HotelDTO> getAllHotelsAvailable();
    Boolean setAllHotels(List<HotelDTO> hotelsUpdate);

}
