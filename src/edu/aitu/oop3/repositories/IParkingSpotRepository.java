package edu.aitu.oop3.repositories;
import edu.aitu.oop3.models.ParkingSpot;
import edu.aitu.oop3.models.ListResult;

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
