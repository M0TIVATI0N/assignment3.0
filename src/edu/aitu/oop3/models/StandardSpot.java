package edu.aitu.oop3.models;

public class StandardSpot extends ParkingSpot {
    public StandardSpot(int id, Tariff tariff, boolean isReserved) {
        super(id, tariff, isReserved, "Standard");
    }
}