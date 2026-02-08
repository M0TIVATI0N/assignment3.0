package edu.aitu.oop3.components.monitoring;

import edu.aitu.oop3.components.reporting.ListResult;
import edu.aitu.oop3.components.reservation.IReservationService;

import java.util.List;

public class ParkingLotManager {
    private static ParkingLotManager instance;
    private final IReservationService reservationService;

    private ParkingLotManager(IReservationService service) {
        this.reservationService = service;
    }

    public static synchronized ParkingLotManager getInstance(IReservationService service) {
        if (instance == null) {
            instance = new ParkingLotManager(service);
        }
        return instance;
    }

    public void displayFreeSpots() {
        ListResult<ParkingSpot> result = reservationService.getAllSpotsAsListResult();
        System.out.println("--- Parking Statistics ---");
        System.out.println("Total parking spots in system: " + result.getTotalCount());

        List<ParkingSpot> freeSpots = reservationService.findFreeSpots();

        if (freeSpots.isEmpty()) {
            System.out.println("Currently, there are no free spots.");
        } else {
            System.out.println("--- Available Spots List ---");
            System.out.printf("%-4s %-12s %-10s %-8s%n", "ID", "Type", "Tariff", "Price/h");
            for (ParkingSpot spot : freeSpots) {
                System.out.printf("%-4d %-12s %-10s %-8d%n",
                        spot.getId(),
                        spot.getType(),
                        spot.getTariff() != null ? spot.getTariff().getName() : "N/A",
                        spot.getTariff() != null ? spot.getTariff().getCost() : 0);
            }
        }
    }
}