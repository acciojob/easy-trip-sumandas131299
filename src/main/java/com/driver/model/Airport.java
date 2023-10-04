package com.driver.model;



public class Airport {

    private String airportName;
    private int noOfTerminals;
    private City city;

    public Airport() {
    }

    public Airport(String airportName, int noOfTerminals, City city) {
        this.airportName = airportName;
        this.noOfTerminals = noOfTerminals;
        this.city = city;
    }

    public String getAirportName() {
        return this.airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public int getNoOfTerminals() {
        return this.noOfTerminals;
    }

    public void setNoOfTerminals(int noOfTerminals) {
        this.noOfTerminals = noOfTerminals;
    }

    public City getCity() {
        return this.city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
