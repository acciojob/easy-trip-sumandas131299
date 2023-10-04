package com.driver.controllers;

import com.driver.model.Airport;
import com.driver.model.City;
import com.driver.model.Flight;
import com.driver.model.Passenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service

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

    public int getNumberOfPeopleOn(Date date, String airportName) {
        return airportRepository.getNumberOfPeopleOn(date,airportName);
    }

    public void addPassenger(Passenger passenger) {
        this.airportRepository.addPassenger(passenger);
    }

    public String getAirportNameFromFlightId(Integer flightId) {
        return this.airportRepository.getAirportNameFromFlightId(flightId);
    }

    public String bookATicket(Integer flightId, Integer passengerId) {
        return this.airportRepository.bookATicket(flightId,passengerId);
    }

    public String cancelATicket(Integer flightId, Integer passengerId) {
        return this.airportRepository.cancelATicket(flightId,passengerId);
    }

    public int countOfBookingsDoneByPassengerAllCombined(Integer passengerId) {
        return this.airportRepository.countOfBookingsDoneByPassengerAllCombined(passengerId);
    }
}
