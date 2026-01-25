package edu.aitu.oop3.models;

public class ParkingSpot {
    private final int id;
    private final Tariff tariff;
    private final boolean isReserved;

    public ParkingSpot(int id, Tariff tariff, boolean isReserved) {
        this.id = id;
        this.tariff = tariff;
       this.isReserved = isReserved;
    }

    public int getId() {
        return id;
    }
    public Tariff getTariff() {
        return tariff;
    }
    public boolean isReserved() {
        return isReserved;
    }
}
