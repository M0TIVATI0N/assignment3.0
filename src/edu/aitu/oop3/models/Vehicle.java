package edu.aitu.oop3.models;

public class Vehicle {
    private final int id;
    private final String licensePlate;

    public Vehicle(int id, String licensePlate) {
        this.id = id;
        this.licensePlate = licensePlate;
    }

    public int getId() {
        return id;
    }

    public String getLicensePlate() {
        return licensePlate;
    }
}
