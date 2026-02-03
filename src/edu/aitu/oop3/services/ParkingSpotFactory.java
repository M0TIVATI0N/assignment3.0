package edu.aitu.oop3.services;
import edu.aitu.oop3.models.*;

public class ParkingSpotFactory {
    public static ParkingSpot createSpot(int id, Tariff tariff, boolean isReserved, String type) {
        return switch (type.toLowerCase()) {
            case "electric" -> new ElectricSpot(id, tariff, isReserved);
            case "disabled" -> new DisabledSpot(id, tariff, isReserved);
            default -> new StandardSpot(id, tariff, isReserved);
        };
    }
}