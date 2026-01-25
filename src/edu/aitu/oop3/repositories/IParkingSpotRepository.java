package edu.aitu.oop3.repositories;
import edu.aitu.oop3.models.ParkingSpot;
import java.util.List;

public interface IParkingSpotRepository {
    List<ParkingSpot> findFree();
    void updateStatus(int id, boolean isReserved);
    ParkingSpot getById(int id);
}
