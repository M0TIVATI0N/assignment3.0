package edu.aitu.oop3.models;

public class DisabledSpot extends ParkingSpot {
    public DisabledSpot(int id, Tariff tariff, boolean isReserved) {
        super(id, tariff, isReserved, "Disabled");
    }
}