package edu.aitu.oop3.models;

import java.util.Date;


public class Reservation {
    private final int id;
    private final Date from;
    private final Date to;
    private final Vehicle vehicle;
    private final ParkingSpot spot;

    public Reservation(int id, Date startDate, Date endDate, Vehicle vehicle, ParkingSpot spot) {
        this.id = id;
        this.from = startDate;
        this.to = endDate;
        this.vehicle = vehicle;
        this.spot = spot;
    }

    public int getId() {
        return id;
    }

    public Date getFrom() {
        return from;
    }

    public Date getTo() {
        return to;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public ParkingSpot getSpot() {
        return spot;
    }
}
