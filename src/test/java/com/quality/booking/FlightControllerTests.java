package com.quality.booking;

import com.quality.booking.controller.FlightController;
import com.quality.booking.dtos.FlightDTO;
import com.quality.booking.repository.implementations.FlightRepositoryImpl;
import com.quality.booking.services.implementations.FlightServiceImpl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@ContextConfiguration(classes=BookingApiApplication.class)
@WebMvcTest(FlightController.class)
class FlightControllerTests {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private FlightRepositoryImpl mockFlightDB;
    @SpyBean
    private FlightServiceImpl flightService;
    @InjectMocks
    private FlightController flightController;


    @Test
    void should_getAllflights() throws Exception{
        List<FlightDTO> flights = createFlights();
        when(mockFlightDB.getAllFlightsAvailable()).thenReturn(flights);
        this.mockMvc.perform(get("/api/v1/flights"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void should_getflightsInRangeDateAndDestination_200() throws Exception{
        List<FlightDTO> flights = createFlights();
        when(mockFlightDB.getAllFlightsAvailable()).thenReturn(flights);
        this.mockMvc.perform(get("/api/v1/flights?dateFrom=10/02/2021&dateTo=20/02/2021&origin=Buenos Aires&destination=Puerto Iguazu"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void should_getflightsInRangeDateAndDestination_404() throws Exception{
        List<FlightDTO> flights = createFlights();
        when(mockFlightDB.getAllFlightsAvailable()).thenReturn(flights);
        this.mockMvc.perform(get("/api/v1/flights?dateFrom=10/02/2021&dateTo=21/03/2021&origin=Buenos Aires&destination=Puerto Viguazu"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void should_getflightsInRangeDateAndDestination_500() throws Exception{
        List<FlightDTO> flights = createFlights();
        when(mockFlightDB.getAllFlightsAvailable()).thenReturn(flights);
        this.mockMvc.perform(get("/api/v1/flights?dateFrom=10/02/1999&dateTo=21/03/1998&origin=Buenos Aires&destination=Puerto Iguazu"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    private List<FlightDTO> createFlights(){
        List<FlightDTO> mockflights = new ArrayList<>();
        FlightDTO f1 = new FlightDTO("F1", "Buenos Aires", "Puerto Iguazu", "Business", 43000, "10-02-2021", "20-02-2021");
        FlightDTO f2 = new FlightDTO("F2", "Puerto Iguazu", "Bogota", "Business", 43200, "10-02-2021", "20-02-2021");
        mockflights.add(f1);
        mockflights.add(f2);

        return mockflights;
    }

}
