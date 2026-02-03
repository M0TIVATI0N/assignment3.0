package edu.aitu.oop3.models;

public class ElectricSpot extends ParkingSpot {
    public ElectricSpot(int id, Tariff tariff, boolean isReserved) {
        super(id, tariff, isReserved, "Electric");
    }
}