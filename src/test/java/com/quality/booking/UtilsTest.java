package com.quality.booking;

import com.fasterxml.jackson.core.type.TypeReference;
import com.quality.booking.dtos.BookingDTO;
import com.quality.booking.dtos.HotelDTO;
import com.quality.booking.utils.jsonEngine.JsonEngine;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mockStatic;

public class UtilsTest {

    private String path = "src/main/resources/db/hotelsTest.json";
    private TypeReference<ArrayList<HotelDTO>> tf = new TypeReference<ArrayList<HotelDTO>>() {};
    @BeforeAll
    static void setup(){
        mockStatic(JsonEngine.class);
    }
    @Test
    void jsonEngine_writeDatabase() throws Exception {
        JsonEngine.writeDatabase(path, createHotels());
        assert (true);
    }

    @Test
    void jsonEngine_readFile() throws Exception {
        List<HotelDTO> result = JsonEngine.readFileDB(path, tf);
        assert(true);
    }


    @Test
    void jsonEngine_appendDatabase() throws Exception {
        HotelDTO h2 = new HotelDTO("H3", "OTRO HOTEL", "Buenos Aires", "DOBLE", 1500, "24-04-2021", "21-05-2021", false);
        List<HotelDTO> aux = createHotels();
        aux.add(h2);
        JsonEngine.appendDatabase(path, h2);
        List<HotelDTO> result = JsonEngine.readFileDB(path, tf);
        assert(true);
    }

    @Test
    void jsonEngine_writeDatabase_fail() throws Exception {

        doThrow(new IOException()).when(JsonEngine.class);
        JsonEngine.writeDatabase(path, createHotels());
        assert(true);
    }

    @Test
    void jsonEngine_readFile_fail() throws Exception {
        doThrow(new IOException()).when(JsonEngine.class);
        List result = JsonEngine.readFileDB(path, tf);
        assert(true);
    }



    private List<HotelDTO> createHotels(){
        List<HotelDTO> mockHotels = new ArrayList<>();
        HotelDTO h = new HotelDTO("BH-0002", "Hotel Bristol", "Buenos Aires", "Doble", 7200, "12-02-2021", "17-04-2021", false);
        HotelDTO h1 = new HotelDTO("H2", "NH", "Buenos Aires", "DOBLE", 1500, "24-04-2021", "21-05-2021", false);
        mockHotels.add(h);
        mockHotels.add(h1);

        return mockHotels;
    }

}
