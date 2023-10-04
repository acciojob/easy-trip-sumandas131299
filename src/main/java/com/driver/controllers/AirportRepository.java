package com.driver.controllers;

import com.driver.model.Airport;
import com.driver.model.City;
import com.driver.model.Flight;
import com.driver.model.Passenger;

import java.util.Date;
import java.util.HashMap;

public class AirportRepository {

    HashMap <String ,Airport> airportmap ;
    HashMap <Integer , Flight > flightHashMap ;
    HashMap <Integer , Passenger > passengerHashMap ;

    AirportRepository(){
        this.airportmap = new HashMap<>();
        this.flightHashMap = new HashMap<>();
        this.passengerHashMap = new HashMap<>();
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

    public int getNumberOfPeopleOn(Date date, String airportName) {
        int count =0;
        for (Flight fly : flightHashMap.values()){

            if((airportName == fly.getFromCity().toString() || airportName==fly.getToCity().toString()) && fly.getFlightDate()==date) count+=fly.getMaxCapacity();
        }
        return count;
    }

    public void addPassenger(Passenger passenger) {
        int id = passenger.getPassengerId();
        passengerHashMap.put(id,passenger);
    }
}
