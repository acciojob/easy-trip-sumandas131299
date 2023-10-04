package com.driver.controllers;

import com.driver.model.Airport;
import com.driver.model.City;
import com.driver.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;

public class AirportService {

    @Autowired
    AirportRepository airportRepository;
    public void addAirport(Airport airport) {
        this.airportRepository.addAirport(airport);
    }

    public String getLargestAirportName() {
       return airportRepository.getLargestAirportName();
    }

    public double getShortestDurationOfPossibleBetweenTwoCities(City fromCity, City toCity) {
        return airportRepository.getShortestDurationOfPossibleBetweenTwoCities(fromCity,toCity);
    }

    public void addFlight(Flight flight) {
        this.airportRepository.addFlight(flight);
    }
}
