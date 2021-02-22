package com.quality.booking;

import com.quality.booking.controller.HotelController;
import com.quality.booking.dtos.HotelDTO;
import com.quality.booking.repository.implementations.HotelRepositoryImpl;
import com.quality.booking.services.implementations.HotelServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
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
@WebMvcTest(HotelController.class)
class HotelControllerTests {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private HotelRepositoryImpl mockHotelDB;
	@SpyBean
	private HotelServiceImpl hotelService;
	@InjectMocks
	private HotelController hotelController;


	@Test
	void should_getAllHotels() throws Exception{
		List<HotelDTO> hotels = createHotels();
		when(mockHotelDB.getAllHotelsAvailable()).thenReturn(hotels);
		this.mockMvc.perform(get("/api/v1/hotels"))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	void should_getHotelsInRangeDateAndDestination_200() throws Exception{
		List<HotelDTO> hotels = createHotels();
		when(mockHotelDB.getAllHotelsAvailable()).thenReturn(hotels);
		this.mockMvc.perform(get("/api/v1/hotels?dateFrom=12/02/2021&dateTo=21/03/2021&destination=Puerto Iguazu"))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	void should_getHotelsInRangeDateAndDestination_404() throws Exception{
		List<HotelDTO> hotels = createHotels();
		when(mockHotelDB.getAllHotelsAvailable()).thenReturn(hotels);
		this.mockMvc.perform(get("/api/v1/hotels?dateFrom=10/02/2021&dateTo=21/03/2021&destination=Puerto Viguazu"))
				.andDo(print())
				.andExpect(status().isNotFound());
	}

	@Test
	void should_getHotelsInRangeDateAndDestination_500() throws Exception{
		List<HotelDTO> hotels = createHotels();
		when(mockHotelDB.getAllHotelsAvailable()).thenReturn(hotels);
		this.mockMvc.perform(get("/api/v1/hotels?dateFrom=10/02/1999&dateTo=21/03/1998&destination=Puerto Viguazu"))
				.andDo(print())
				.andExpect(status().isBadRequest());
	}

	private List<HotelDTO> createHotels(){
		List<HotelDTO> mockHotels = new ArrayList<>();
		HotelDTO h = new HotelDTO("H1", "HILTON", "Puerto Iguazu", "DOBLE", 1200, "12-02-2021", "21-03-2021", false);
		HotelDTO h1 = new HotelDTO("H2", "NH", "Buenos Aires", "DOBLE", 1500, "24-04-2021", "21-05-2021", false);
		mockHotels.add(h);
		mockHotels.add(h1);

		return mockHotels;
	}

}
