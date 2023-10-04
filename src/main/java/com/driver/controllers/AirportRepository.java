package com.driver.controllers;

import com.driver.model.Airport;
import com.driver.model.City;
import com.driver.model.Flight;
import com.driver.model.Passenger;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class AirportRepository {

    HashMap <String ,Airport> airportmap ;
    HashMap <Integer , Flight > flightHashMap ;
    HashMap <Integer , Passenger > passengerHashMap ;
    HashMap <Integer , ArrayList<Integer>> Noofpassinger ;

    AirportRepository(){
        this.airportmap = new HashMap<>();
        this.flightHashMap = new HashMap<>();
        this.passengerHashMap = new HashMap<>();
        this.Noofpassinger = new HashMap<>();
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

            if((airportName == fly.getFromCity().name() || airportName==fly.getToCity().name()) && fly.getFlightDate()==date) count+=fly.getMaxCapacity();
        }
        return count;
    }

    public void addPassenger(Passenger passenger) {
        int id = passenger.getPassengerId();
        passengerHashMap.put(id,passenger);
    }

    public String getAirportNameFromFlightId(Integer flightId) {
        for(Flight id : flightHashMap.values()){
            if(id.getFlightId() == flightId){
                return id.getFromCity().name();
            }
        }return null;
    }

    public String bookATicket(Integer flightId, Integer passengerId) {

        for( Flight fly : flightHashMap.values()){
           if(fly.getFlightId()==flightId) {
               if (fly.getMaxCapacity() > Noofpassinger.get(flightId).size()) return "FAILURE";
           }
        }

        if(Noofpassinger.get(flightId).contains(passengerId)) return "FAILURE";
        else Noofpassinger.get(flightId).add(passengerId);
        return "SUCCESS";

    }

    public String cancelATicket(Integer flightId, Integer passengerId) {
        if(!Noofpassinger.containsKey(flightId)) return "FAILURE";
        if (!Noofpassinger.get(flightId).contains(passengerId)) return "FAILURE";

        for(int pass : Noofpassinger.get(flightId)){
            if(pass == passengerId){
                Noofpassinger.get(flightId).remove(passengerId);
                return "SUCCESS";
            }
        }
        return "FAILURE";
    }
}
