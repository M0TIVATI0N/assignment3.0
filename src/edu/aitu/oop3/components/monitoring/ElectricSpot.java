package edu.aitu.oop3.components.monitoring;

import edu.aitu.oop3.components.payment.Tariff;

public class ElectricSpot extends ParkingSpot {
    public ElectricSpot(int id, Tariff tariff, boolean isReserved) {
        super(id, tariff, isReserved, "Electric");
    }
}