package com.driver.controllers;

import com.driver.model.Airport;
import org.springframework.beans.factory.annotation.Autowired;

public class AirportService {

    @Autowired
    AirportRepository airportRepository;
    public String addAirport(Airport airport) {
       return this.airportRepository.addAirport(airport);
    }
}
