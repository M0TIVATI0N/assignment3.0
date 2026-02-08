package edu.aitu.oop3.components.monitoring;

import edu.aitu.oop3.components.payment.Tariff;

public class DisabledSpot extends ParkingSpot {
    public DisabledSpot(int id, Tariff tariff, boolean isReserved) {
        super(id, tariff, isReserved, "Disabled");
    }
}