package com.quality.booking.controller;

import com.quality.booking.dtos.HotelDTO;
import com.quality.booking.services.implementations.HotelServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping(params = {"dateFrom", "dateTo","destination"})
    public List<HotelDTO> getHotelInRangeDateAndDestination(@RequestParam String dateFrom, @RequestParam String dateTo, @RequestParam String destination){
        return hotelService.getHotelInRangeDateAndDestination(dateFrom, dateTo, destination);
    }

}
