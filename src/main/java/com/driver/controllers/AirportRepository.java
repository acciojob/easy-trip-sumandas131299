package com.driver.controllers;

import com.driver.model.Airport;
import com.driver.model.City;
import com.driver.model.Flight;
import com.driver.model.Passenger;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

@Repository

public class AirportRepository {

    int calculateFlightFare = 0;
    HashMap<String, Airport> airportmap = new HashMap();
    HashMap<Integer, Flight> flightHashMap = new HashMap();
    HashMap<Integer, Passenger> passengerHashMap = new HashMap();
    HashMap<Integer, ArrayList<Integer>> Noofpassinger = new HashMap();

    AirportRepository() {
    }

    public void addAirport(Airport airport) {
        String name = airport.getAirportName();
        this.airportmap.put(name, airport);
    }

    public String getLargestAirportName() {
        int max = 0;
        String name = "";
        Iterator var3 = this.airportmap.values().iterator();

        while(var3.hasNext()) {
            Airport air = (Airport)var3.next();
            if (air.getNoOfTerminals() > max) {
                max = air.getNoOfTerminals();
                name = air.getAirportName();
            } else if (air.getNoOfTerminals() == max) {
                int retval = name.compareTo(air.getAirportName());
                if (retval < 0) {
                    name = air.getAirportName();
                }
            }
        }

        return name;
    }

    public double getShortestDurationOfPossibleBetweenTwoCities(City fromCity, City toCity) {
        City source = fromCity;
        City destination = toCity;
        double duration = 2.147483647E9;
        Iterator var7 = this.flightHashMap.values().iterator();

        while(var7.hasNext()) {
            Flight fly = (Flight)var7.next();
            if (fly.getFromCity() == source && fly.getToCity() == destination) {
                duration = Math.min(duration, fly.getDuration());
            }
        }

        if (duration == 2.147483647E9) {
            return -1.0;
        } else {
            return duration;
        }
    }

    public void addFlight(Flight flight) {
        int id = flight.getFlightId();
        this.flightHashMap.put(id, flight);
    }

    public int getNumberOfPeopleOn(Date date, String airportName) {
        int count = 0;
        Iterator var4 = this.flightHashMap.values().iterator();

        while(true) {
            Flight fly;
            do {
                if (!var4.hasNext()) {
                    return count;
                }

                fly = (Flight)var4.next();
            } while(airportName != fly.getFromCity().name() && airportName != fly.getToCity().name());

            if (fly.getFlightDate() == date) {
                count += fly.getMaxCapacity();
            }
        }
    }

    public void addPassenger(Passenger passenger) {
        int id = passenger.getPassengerId();
        this.passengerHashMap.put(id, passenger);
    }

    public String getAirportNameFromFlightId(Integer flightId) {
        Iterator var2 = this.flightHashMap.values().iterator();

        while(var2.hasNext()) {
            Flight id = (Flight)var2.next();
            if (id.getFlightId() == flightId) {
                return id.getFromCity().name();
            }
        }

        return null;
    }

    public String bookATicket(Integer flightId, Integer passengerId) {
        Iterator var3 = this.flightHashMap.values().iterator();

        Flight fly;
        do {
            if (!var3.hasNext()) {
                if (((ArrayList)this.Noofpassinger.get(flightId)).contains(passengerId)) {
                    return "FAILURE";
                }

                ((ArrayList)this.Noofpassinger.get(flightId)).add(passengerId);
                return "SUCCESS";
            }

            fly = (Flight)var3.next();
        } while(fly.getFlightId() != flightId || fly.getMaxCapacity() <= ((ArrayList)this.Noofpassinger.get(flightId)).size());

        return "FAILURE";
    }

    public String cancelATicket(Integer flightId, Integer passengerId) {
        if (!this.Noofpassinger.containsKey(flightId)) {
            return "FAILURE";
        } else if (!((ArrayList)this.Noofpassinger.get(flightId)).contains(passengerId)) {
            return "FAILURE";
        } else {
            Iterator var3 = ((ArrayList)this.Noofpassinger.get(flightId)).iterator();

            while(var3.hasNext()) {
                int pass = (Integer)var3.next();
                if (pass == passengerId) {
                    ((ArrayList)this.Noofpassinger.get(flightId)).remove(passengerId);
                    return "SUCCESS";
                }
            }

            return "FAILURE";
        }
    }

    public int countOfBookingsDoneByPassengerAllCombined(Integer passengerId) {
        int count = 0;
        Iterator var3 = this.Noofpassinger.values().iterator();

        while(var3.hasNext()) {
            ArrayList<Integer> list = (ArrayList)var3.next();
            if (list.contains(passengerId)) {
                ++count;
            }
        }

        return count;
    }

    public int calculateRevenueOfAFlight(Integer flightId) {
        return ((ArrayList)this.Noofpassinger.get(flightId)).size() * (3000 + (((ArrayList)this.Noofpassinger.get(flightId)).size() - 1) * 50);
    }

    public int calculateFlightFare(Integer flightId) {
        return 3000 + (((ArrayList)this.Noofpassinger.get(flightId)).size() - 1) * 50;
    }
}
