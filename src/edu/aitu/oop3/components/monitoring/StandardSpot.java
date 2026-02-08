package edu.aitu.oop3.components.monitoring;

import edu.aitu.oop3.components.payment.Tariff;

public class StandardSpot extends ParkingSpot {
    public StandardSpot(int id, Tariff tariff, boolean isReserved) {
        super(id, tariff, isReserved, "Standard");
    }
}