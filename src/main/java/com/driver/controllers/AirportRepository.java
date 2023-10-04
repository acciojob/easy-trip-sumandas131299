package com.driver.controllers;

import com.driver.model.Airport;

import java.util.HashMap;

public class AirportRepository {

    HashMap <String ,Airport> airportmap ;

    AirportRepository(){
        this.airportmap = new HashMap<>();
    }
    public String addAirport(Airport airport) {
       String name =  airport.getAirportName();
       airportmap.put(name,airport);
        return "SUCCESS";
    }
}
