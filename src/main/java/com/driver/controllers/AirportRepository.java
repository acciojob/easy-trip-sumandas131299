package com.driver.controllers;

import com.driver.model.Airport;
import com.driver.model.City;
import com.driver.model.Flight;

import java.util.HashMap;

public class AirportRepository {

    HashMap <String ,Airport> airportmap ;
    HashMap <Integer , Flight > flightHashMap ;

    AirportRepository(){
        this.airportmap = new HashMap<>();
        this.flightHashMap = new HashMap<>();
    }
    public void addAirport(Airport airport) {
       String name =  airport.getAirportName();
       airportmap.put(name,airport);

    }

    public String getLargestAirportName() {
        int max=0;
        String name = "";
        for (Airport air : airportmap.values()){
            if(air.getNoOfTerminals()>max) {
                max=air.getNoOfTerminals();
                name = air.getAirportName();
            } else if (air.getNoOfTerminals() == max) {
                int retval = name.compareTo(air.getAirportName());
                if(retval<0) name = air.getAirportName();
            }
        }
        return name;
    }

    public double getShortestDurationOfPossibleBetweenTwoCities(City fromCity, City toCity) {
        City source = fromCity;
        City destination = toCity;
        double duration=Integer.MAX_VALUE;
        for (Flight fly : flightHashMap.values()){
            if(fly.getFromCity()==source && fly.getToCity()==destination) duration = Math.min(duration,fly.getDuration());
        }
        if(duration == Integer.MAX_VALUE) return -1;
        else return duration;
    }

    public void addFlight(Flight flight) {
        int id = flight.getFlightId();
        flightHashMap.put(id,flight);
    }
}
