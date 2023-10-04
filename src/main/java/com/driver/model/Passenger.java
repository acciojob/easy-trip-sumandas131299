package com.driver.model;

public class Passenger {

    private int passengerId;
    private String email;
    private String name;
    private int age;

    public Passenger() {
    }

    public Passenger(int passengerId, String email, String name, int age) {
        this.passengerId = passengerId;
        this.email = email;
        this.name = name;
        this.age = age;
    }

    public int getPassengerId() {
        return this.passengerId;
    }

    public void setPassengerId(int passengerId) {
        this.passengerId = passengerId;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
