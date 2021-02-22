package com.quality.booking.controller;

import com.quality.booking.dtos.FlightDTO;
import com.quality.booking.services.implementations.FlightServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/flights")
public class FlightController {
    private FlightServiceImpl flightService;

    public FlightController(FlightServiceImpl flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public List<FlightDTO> getAll(){
        return flightService.getAllFlightsAvailable();
    }

    @GetMapping(params = {"dateFrom", "dateTo", "origin", "destination"})
    public List<FlightDTO> getFlightsInRangeDateAndDestination(@RequestParam String dateFrom, @RequestParam String dateTo, @RequestParam String origin, @RequestParam String destination){
        return flightService.getFlightsInRangeDate(dateFrom, dateTo, origin, destination);
    }
}
