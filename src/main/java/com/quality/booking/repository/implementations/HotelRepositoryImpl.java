package com.quality.booking.repository.implementations;

import com.fasterxml.jackson.core.type.TypeReference;
import com.quality.booking.dtos.HotelDTO;
import com.quality.booking.repository.interfaces.HotelRepository;
import com.quality.booking.utils.jsonEngine.JsonEngine;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * this class implement HotelRepository and this simulate a db connector.
 *
 * @author frivarola
 */
@Repository
public class HotelRepositoryImpl implements HotelRepository {
    private static List<HotelDTO> hotels = null;
    private static String path = "src/main/resources/db/hotels.json";

    static {
        loadDatabase();
    }

    /**
     * Return all hotels available on database
     *
     * @return all hotel available
     */
    @Override
    public List<HotelDTO> getAllHotelsAvailable() {
        return hotels;
    }

    /**
     * Update hotels db
     * return false is process failed. Else return true.
     * @param hotelsUpdate new hotels list
     * @return
     */
    public Boolean setAllHotels(List<HotelDTO> hotelsUpdate) {
        if (hotelsUpdate != null) {
            this.hotels = hotelsUpdate;

            try {

                JsonEngine.writeDatabase(path, this.hotels);

            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }

    /**
     * This method load database from json file
     */
    private static void loadDatabase() {
        TypeReference<ArrayList<HotelDTO>> tf = new TypeReference<>() {
        };

        try {
            hotels = JsonEngine.readFileDB(path, tf);
        } catch (Exception e) {
            e.printStackTrace();
            hotels = new ArrayList<>();
        }
    }
}
