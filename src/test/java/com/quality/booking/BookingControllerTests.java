package com.quality.booking;

import com.quality.booking.controller.BookingController;
import com.quality.booking.dtos.BookingDTO;
import com.quality.booking.dtos.FlightDTO;
import com.quality.booking.dtos.FlightReservationDTO;
import com.quality.booking.dtos.HotelDTO;
import com.quality.booking.repository.implementations.BookingRepositoryImpl;
import com.quality.booking.services.implementations.BookingServiceImpl;
import com.quality.booking.services.implementations.FlightServiceImpl;
import com.quality.booking.services.implementations.HotelServiceImpl;
import com.quality.booking.utils.jsonEngine.JsonEngine;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes=BookingApiApplication.class)
@WebMvcTest(BookingController.class)
public class BookingControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookingRepositoryImpl mockDB;
    @MockBean
    private FlightServiceImpl mockFlightService;
    @MockBean
    private HotelServiceImpl mockHotelService;
    @MockBean
    private JsonEngine jsonEngine;


    @SpyBean
    private BookingServiceImpl bookingService;

    @InjectMocks
    private BookingController bookingController;

    @Test
    void should_bookingReservation() throws Exception{
        List<HotelDTO> hotels = createHotels();

        when(mockHotelService.getHotelInRangeDateAndDestination("12/02/2021", "17/04/2021", "Buenos Aires")).thenReturn(hotels);
        this.mockMvc.perform(post("/api/v1/booking")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"username\": \"arjonamiguel@gmail.com\",\n" +
                        "  \"booking\": {\n" +
                        "    \"hotelCode\": \"BH-0002\",\n" +
                        "    \"destination\": \"Buenos Aires\",\n" +
                        "    \"roomType\": \"Doble\",\n" +
                        "    \"dateFrom\": \"12/02/2021\",\n" +
                        "    \"dateTo\": \"17/04/2021\",\n" +
                        "    \"peopleAmount\": 2,\n" +
                        "    \"people\": [\n" +
                        "        {\"dni\": 39635896, \"name\":\"Federico\", \"lastname\":\"Rivarola\", \"birthDate\":\"03/10/1996\", \"mail\": \"federiva@outlook.com\"},\n" +
                        "        {\"dni\": 34733166, \"name\":\"Leonel\", \"lastname\":\"Messi\", \"birthDate\":\"24/06/1987\", \"mail\": \"leome@barca.es\"}\n" +
                        "    ],\n" +
                        "    \"paymentMethod\": {\n" +
                        "        \"type\": \"CREDIT\",\n" +
                        "        \"number\": \"1234-1234-1234-1234\",\n" +
                        "        \"dues\": 3\n" +
                        "    }\n" +
                        "  }\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void should_bookingReservation_hotels_isEmpty() throws Exception{
        List<HotelDTO> hotels = new ArrayList<>();

        when(mockHotelService.getHotelInRangeDateAndDestination("12/02/2021", "17/04/2021", "Buenos Aires")).thenReturn(hotels);
        this.mockMvc.perform(post("/api/v1/booking")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"username\": \"arjonamiguel@gmail.com\",\n" +
                        "  \"booking\": {\n" +
                        "    \"hotelCode\": \"BH-0002\",\n" +
                        "    \"destination\": \"Buenos Aires\",\n" +
                        "    \"roomType\": \"Doble\",\n" +
                        "    \"dateFrom\": \"12/02/2021\",\n" +
                        "    \"dateTo\": \"17/04/2021\",\n" +
                        "    \"peopleAmount\": 2,\n" +
                        "    \"people\": [\n" +
                        "        {\"dni\": 39635896, \"name\":\"Federico\", \"lastname\":\"Rivarola\", \"birthDate\":\"03/10/1996\", \"mail\": \"federiva@outlook.com\"},\n" +
                        "        {\"dni\": 34733166, \"name\":\"Leonel\", \"lastname\":\"Messi\", \"birthDate\":\"24/06/1987\", \"mail\": \"leome@barca.es\"}\n" +
                        "    ],\n" +
                        "    \"paymentMethod\": {\n" +
                        "        \"type\": \"CREDIT\",\n" +
                        "        \"number\": \"1234-1234-1234-1234\",\n" +
                        "        \"dues\": 3\n" +
                        "    }\n" +
                        "  }\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void should_bookingReservation_booking_null() throws Exception{
        List<HotelDTO> hotels = new ArrayList<>();

        when(mockHotelService.getHotelInRangeDateAndDestination("12/02/2021", "17/04/2021", "Buenos Aires")).thenReturn(hotels);
        this.mockMvc.perform(post("/api/v1/booking")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"username\": \"arjonamiguel@gmail.com\"" +
                        "}"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_bookingReservation_room_fail() throws Exception{
        List<HotelDTO> hotels = createHotels();

        when(mockHotelService.getHotelInRangeDateAndDestination("12/02/2021", "17/04/2021", "Buenos Aires")).thenReturn(hotels);
        this.mockMvc.perform(post("/api/v1/booking")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"username\": \"arjonamiguel@gmail.com\",\n" +
                        "  \"booking\": {\n" +
                        "    \"hotelCode\": \"BH-0002\",\n" +
                        "    \"destination\": \"Buenos Aires\",\n" +
                        "    \"roomType\": \"Doble\",\n" +
                        "    \"dateFrom\": \"12/02/2021\",\n" +
                        "    \"dateTo\": \"17/04/2021\",\n" +
                        "    \"peopleAmount\": 3,\n" +
                        "    \"people\": [\n" +
                        "        {\"dni\": 39635896, \"name\":\"Federico\", \"lastname\":\"Rivarola\", \"birthDate\":\"03/10/1996\", \"mail\": \"federiva@outlook.com\"},\n" +
                        "        {\"dni\": 34733166, \"name\":\"Leonel\", \"lastname\":\"Messi\", \"birthDate\":\"24/06/1987\", \"mail\": \"leome@barca.es\"}\n" +
                        "    ],\n" +
                        "    \"paymentMethod\": {\n" +
                        "        \"type\": \"CREDIT\",\n" +
                        "        \"number\": \"1234-1234-1234-1234\",\n" +
                        "        \"dues\": 3\n" +
                        "    }\n" +
                        "  }\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_bookingReservation_mail_fail() throws Exception{
        List<HotelDTO> hotels = createHotels();

        when(mockHotelService.getHotelInRangeDateAndDestination("12/02/2021", "17/04/2021", "Buenos Aires")).thenReturn(hotels);
        this.mockMvc.perform(post("/api/v1/booking")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"username\": \"arjonamiguel@gmail.com\",\n" +
                        "  \"booking\": {\n" +
                        "    \"hotelCode\": \"BH-0002\",\n" +
                        "    \"destination\": \"Buenos Aires\",\n" +
                        "    \"roomType\": \"Doble\",\n" +
                        "    \"dateFrom\": \"12/02/2021\",\n" +
                        "    \"dateTo\": \"17/04/2021\",\n" +
                        "    \"peopleAmount\": 2,\n" +
                        "    \"people\": [\n" +
                        "        {\"dni\": 39635896, \"name\":\"Federico\", \"lastname\":\"Rivarola\", \"birthDate\":\"03/10/1996\", \"mail\": \"feder\"},\n" +
                        "        {\"dni\": 34733166, \"name\":\"Leonel\", \"lastname\":\"Messi\", \"birthDate\":\"24/06/1987\", \"mail\": \"leome@barca.es\"}\n" +
                        "    ],\n" +
                        "    \"paymentMethod\": {\n" +
                        "        \"type\": \"CREDIT\",\n" +
                        "        \"number\": \"1234-1234-1234-1234\",\n" +
                        "        \"dues\": 3\n" +
                        "    }\n" +
                        "  }\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_bookingReservation_dateFormat_fail() throws Exception{
        List<HotelDTO> hotels = createHotels();

        when(mockHotelService.getHotelInRangeDateAndDestination("12/02/2021", "17/04/2021", "Buenos Aires")).thenReturn(hotels);
        this.mockMvc.perform(post("/api/v1/booking")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"username\": \"arjonamiguel@gmail.com\",\n" +
                        "  \"booking\": {\n" +
                        "    \"hotelCode\": \"BH-0002\",\n" +
                        "    \"destination\": \"Buenos Aires\",\n" +
                        "    \"roomType\": \"Doble\",\n" +
                        "    \"dateFrom\": \"12-02-2021\",\n" +
                        "    \"dateTo\": \"17/04/2021\",\n" +
                        "    \"peopleAmount\": 2,\n" +
                        "    \"people\": [\n" +
                        "        {\"dni\": 39635896, \"name\":\"Federico\", \"lastname\":\"Rivarola\", \"birthDate\":\"03/10/1996\", \"mail\": \"feder\"},\n" +
                        "        {\"dni\": 34733166, \"name\":\"Leonel\", \"lastname\":\"Messi\", \"birthDate\":\"24/06/1987\", \"mail\": \"leome@barca.es\"}\n" +
                        "    ],\n" +
                        "    \"paymentMethod\": {\n" +
                        "        \"type\": \"CREDIT\",\n" +
                        "        \"number\": \"1234-1234-1234-1234\",\n" +
                        "        \"dues\": 3\n" +
                        "    }\n" +
                        "  }\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_bookingReservation_dateToIsBeforeFrom_fail() throws Exception{
        List<HotelDTO> hotels = createHotels();

        when(mockHotelService.getHotelInRangeDateAndDestination("12/02/2021", "17/04/2021", "Buenos Aires")).thenReturn(hotels);
        this.mockMvc.perform(post("/api/v1/booking")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"username\": \"arjonamiguel@gmail.com\",\n" +
                        "  \"booking\": {\n" +
                        "    \"hotelCode\": \"BH-0002\",\n" +
                        "    \"destination\": \"Buenos Aires\",\n" +
                        "    \"roomType\": \"Doble\",\n" +
                        "    \"dateFrom\": \"12/02/2021\",\n" +
                        "    \"dateTo\": \"17/01/2021\",\n" +
                        "    \"peopleAmount\": 2,\n" +
                        "    \"people\": [\n" +
                        "        {\"dni\": 39635896, \"name\":\"Federico\", \"lastname\":\"Rivarola\", \"birthDate\":\"03/10/1996\", \"mail\": \"feder\"},\n" +
                        "        {\"dni\": 34733166, \"name\":\"Leonel\", \"lastname\":\"Messi\", \"birthDate\":\"24/06/1987\", \"mail\": \"leome@barca.es\"}\n" +
                        "    ],\n" +
                        "    \"paymentMethod\": {\n" +
                        "        \"type\": \"CREDIT\",\n" +
                        "        \"number\": \"1234-1234-1234-1234\",\n" +
                        "        \"dues\": 3\n" +
                        "    }\n" +
                        "  }\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_flightReservation() throws Exception{
        List<FlightDTO> flights = createFlights();
        when(mockFlightService.getFlightsInRangeDate("12/02/2021", "23/02/2021", "Tucuman", "Puerto Iguazu")).thenReturn(flights);
        this.mockMvc.perform(post("/api/v1/flight-reservation")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"username\": \"arjonamiguel@gmail.com\",\n" +
                        "  \"flightReservation\": {\n" +
                        "    \"flightNumber\": \"TUPI-3369\",\n" +
                        "    \"origin\": \"Tucuman\",\n" +
                        "    \"destination\": \"Puerto Iguazu\",\n" +
                        "    \"seatType\": \"Economy\",\n" +
                        "    \"dateFrom\": \"12/02/2021\",\n" +
                        "    \"dateTo\": \"23/02/2021\",\n" +
                        "    \"seats\": 2,\n" +
                        "    \"people\": [\n" +
                        "        {\"dni\": 39635896, \"name\":\"Federico\", \"lastname\":\"Rivarola\", \"birthDate\":\"03/10/1996\", \"mail\": \"federiva@outlook.com\"},\n" +
                        "        {\"dni\": 34733166, \"name\":\"Leonel\", \"lastname\":\"Messi\", \"birthDate\":\"24/06/1987\", \"mail\": \"leome@barca.es\"}\n" +
                        "    ],\n" +
                        "    \"paymentMethod\": {\n" +
                        "        \"type\": \"CREDIT\",\n" +
                        "        \"number\": \"1234-1234-1234-1234\",\n" +
                        "        \"dues\": 6\n" +
                        "    }\n" +
                        "  }\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void should_flightReservation_debit_fail() throws Exception{
        List<FlightDTO> flights = createFlights();
        when(mockFlightService.getFlightsInRangeDate("12/02/2021", "23/02/2021", "Tucuman", "Puerto Iguazu")).thenReturn(flights);
        this.mockMvc.perform(post("/api/v1/flight-reservation")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"username\": \"arjonamiguel@gmail.com\",\n" +
                        "  \"flightReservation\": {\n" +
                        "    \"flightNumber\": \"TUPI-3369\",\n" +
                        "    \"origin\": \"Tucuman\",\n" +
                        "    \"destination\": \"Puerto Iguazu\",\n" +
                        "    \"seatType\": \"Economy\",\n" +
                        "    \"dateFrom\": \"12/02/2021\",\n" +
                        "    \"dateTo\": \"23/02/2021\",\n" +
                        "    \"seats\": 2,\n" +
                        "    \"people\": [\n" +
                        "        {\"dni\": 39635896, \"name\":\"Federico\", \"lastname\":\"Rivarola\", \"birthDate\":\"03/10/1996\", \"mail\": \"federiva@outlook.com\"},\n" +
                        "        {\"dni\": 34733166, \"name\":\"Leonel\", \"lastname\":\"Messi\", \"birthDate\":\"24/06/1987\", \"mail\": \"leome@barca.es\"}\n" +
                        "    ],\n" +
                        "    \"paymentMethod\": {\n" +
                        "        \"type\": \"DEBIT\",\n" +
                        "        \"number\": \"1234-1234-1234-1234\",\n" +
                        "        \"dues\": 6\n" +
                        "    }\n" +
                        "  }\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_bookingException() throws Exception{
        List<HotelDTO> hotels = createHotels();
        when(mockHotelService.getHotelInRangeDateAndDestination("12/02/2021", "17/04/2021", "Buenos Aires")).thenReturn(hotels);
        doThrow(new Exception()).when(mockDB).booking(any(BookingDTO.class));

        this.mockMvc.perform(post("/api/v1/booking")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"username\": \"arjonamiguel@gmail.com\",\n" +
                        "  \"booking\": {\n" +
                        "    \"hotelCode\": \"BH-0002\",\n" +
                        "    \"destination\": \"Buenos Aires\",\n" +
                        "    \"roomType\": \"Doble\",\n" +
                        "    \"dateFrom\": \"12/02/2021\",\n" +
                        "    \"dateTo\": \"17/04/2021\",\n" +
                        "    \"peopleAmount\": 2,\n" +
                        "    \"people\": [\n" +
                        "        {\"dni\": 39635896, \"name\":\"Federico\", \"lastname\":\"Rivarola\", \"birthDate\":\"03/10/1996\", \"mail\": \"federiva@outlook.com\"},\n" +
                        "        {\"dni\": 34733166, \"name\":\"Leonel\", \"lastname\":\"Messi\", \"birthDate\":\"24/06/1987\", \"mail\": \"leome@barca.es\"}\n" +
                        "    ],\n" +
                        "    \"paymentMethod\": {\n" +
                        "        \"type\": \"CREDIT\",\n" +
                        "        \"number\": \"1234-1234-1234-1234\",\n" +
                        "        \"dues\": 3\n" +
                        "    }\n" +
                        "  }\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    void should_flightReservation_Exception() throws Exception{
        List<FlightDTO> flights = createFlights();
        when(mockFlightService.getFlightsInRangeDate("12/02/2021", "23/02/2021", "Tucuman", "Puerto Iguazu")).thenReturn(flights);
        doThrow(new Exception("error")).when(mockDB).reservationFlight(any(FlightReservationDTO.class));
        this.mockMvc.perform(post("/api/v1/flight-reservation")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"username\": \"arjonamiguel@gmail.com\",\n" +
                        "  \"flightReservation\": {\n" +
                        "    \"flightNumber\": \"TUPI-3369\",\n" +
                        "    \"origin\": \"Tucuman\",\n" +
                        "    \"destination\": \"Puerto Iguazu\",\n" +
                        "    \"seatType\": \"Economy\",\n" +
                        "    \"dateFrom\": \"12/02/2021\",\n" +
                        "    \"dateTo\": \"23/02/2021\",\n" +
                        "    \"seats\": 2,\n" +
                        "    \"people\": [\n" +
                        "        {\"dni\": 39635896, \"name\":\"Federico\", \"lastname\":\"Rivarola\", \"birthDate\":\"03/10/1996\", \"mail\": \"federiva@outlook.com\"},\n" +
                        "        {\"dni\": 34733166, \"name\":\"Leonel\", \"lastname\":\"Messi\", \"birthDate\":\"24/06/1987\", \"mail\": \"leome@barca.es\"}\n" +
                        "    ],\n" +
                        "    \"paymentMethod\": {\n" +
                        "        \"type\": \"CREDIT\",\n" +
                        "        \"number\": \"1234-1234-1234-1234\",\n" +
                        "        \"dues\": 6\n" +
                        "    }\n" +
                        "  }\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    void should_bookingReservation_hotel_notFound() throws Exception{
        List<HotelDTO> hotels = new ArrayList<>();

        when(mockHotelService.getHotelInRangeDateAndDestination("12/02/2021", "17/04/2021", "Buenos Aires")).thenReturn(hotels);
        this.mockMvc.perform(post("/api/v1/booking")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"username\": \"arjonamiguel@gmail.com\",\n" +
                        "  \"booking\": {\n" +
                        "    \"hotelCode\": \"BH-0002\",\n" +
                        "    \"destination\": \"Buenos Aires\",\n" +
                        "    \"roomType\": \"Doble\",\n" +
                        "    \"dateFrom\": \"12/02/2021\",\n" +
                        "    \"dateTo\": \"17/04/2021\",\n" +
                        "    \"peopleAmount\": 2,\n" +
                        "    \"people\": [\n" +
                        "        {\"dni\": 39635896, \"name\":\"Federico\", \"lastname\":\"Rivarola\", \"birthDate\":\"03/10/1996\", \"mail\": \"federiva@outlook.com\"},\n" +
                        "        {\"dni\": 34733166, \"name\":\"Leonel\", \"lastname\":\"Messi\", \"birthDate\":\"24/06/1987\", \"mail\": \"leome@barca.es\"}\n" +
                        "    ],\n" +
                        "    \"paymentMethod\": {\n" +
                        "        \"type\": \"CREDIT\",\n" +
                        "        \"number\": \"1234-1234-1234-1234\",\n" +
                        "        \"dues\": 3\n" +
                        "    }\n" +
                        "  }\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void should_flightReservation_flight_null() throws Exception{
        List<FlightDTO> flights = createFlights();
        when(mockFlightService.getFlightsInRangeDate(anyString(),anyString(),anyString(), anyString())).thenReturn(flights);
        this.mockMvc.perform(post("/api/v1/flight-reservation")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"username\": \"arjonamiguel@gmail.com\"" +
                        "}"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_flightReservation_flight_notFound() throws Exception{
        List<FlightDTO> flights = new ArrayList<>();
        when(mockFlightService.getFlightsInRangeDate(anyString(),anyString(),anyString(), anyString())).thenReturn(flights);
        this.mockMvc.perform(post("/api/v1/flight-reservation")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"username\": \"arjonamiguel@gmail.com\",\n" +
                        "  \"flightReservation\": {\n" +
                        "    \"flightNumber\": \"TUPI-3369\",\n" +
                        "    \"origin\": \"Tucuman\",\n" +
                        "    \"destination\": \"Puerto Iguazu\",\n" +
                        "    \"seatType\": \"Economy\",\n" +
                        "    \"dateFrom\": \"12/02/2021\",\n" +
                        "    \"dateTo\": \"23/02/2021\",\n" +
                        "    \"seats\": 2,\n" +
                        "    \"people\": [\n" +
                        "        {\"dni\": 39635896, \"name\":\"Federico\", \"lastname\":\"Rivarola\", \"birthDate\":\"03/10/1996\", \"mail\": \"federiva@outlook.com\"},\n" +
                        "        {\"dni\": 34733166, \"name\":\"Leonel\", \"lastname\":\"Messi\", \"birthDate\":\"24/06/1987\", \"mail\": \"leome@barca.es\"}\n" +
                        "    ],\n" +
                        "    \"paymentMethod\": {\n" +
                        "        \"type\": \"CREDIT\",\n" +
                        "        \"number\": \"1234-1234-1234-1234\",\n" +
                        "        \"dues\": 6\n" +
                        "    }\n" +
                        "  }\n" +
                        "}"))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    private List<HotelDTO> createHotels(){
        List<HotelDTO> mockHotels = new ArrayList<>();
        HotelDTO h = new HotelDTO("BH-0002", "Hotel Bristol", "Buenos Aires", "Doble", 7200, "12-02-2021", "17-04-2021", false);
        HotelDTO h1 = new HotelDTO("H2", "NH", "Buenos Aires", "DOBLE", 1500, "24-04-2021", "21-05-2021", false);
        mockHotels.add(h);
        mockHotels.add(h1);

        return mockHotels;
    }

    private List<FlightDTO> createFlights(){
        List<FlightDTO> mockflights = new ArrayList<>();
        FlightDTO f1 = new FlightDTO("TUPI-3369", "Tucuman", "Puerto Iguazu", "Economy", 5400, "02-01-2021", "16-01-2021");
        FlightDTO f2 = new FlightDTO("F2", "Puerto Iguazu", "Bogota", "Business", 43200, "10-02-2021", "20-02-2021");
        mockflights.add(f1);
        mockflights.add(f2);

        return mockflights;
    }
}
