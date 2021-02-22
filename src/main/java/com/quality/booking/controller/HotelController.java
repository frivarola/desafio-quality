package com.quality.booking.controller;

import com.quality.booking.dtos.HotelDTO;
import com.quality.booking.services.implementations.HotelServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/hotels")
public class HotelController {
    private HotelServiceImpl hotelService;

    public HotelController(HotelServiceImpl hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping
    public List<HotelDTO> getAll(){
        return hotelService.getAllHotelsAvailable();
    }

}
