package edu.aitu.oop3.components.monitoring;
import edu.aitu.oop3.components.reporting.ListResult;

public interface IParkingSpotRepository {
    ListResult<ParkingSpot> getAllSpots();
    void updateSpotStatus(int id, boolean isReserved);
    default ParkingSpot getById(int id) {
        return getAllSpots().getItems().stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElse(null);
    }
}
