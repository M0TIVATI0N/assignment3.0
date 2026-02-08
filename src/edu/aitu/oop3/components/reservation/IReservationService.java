package edu.aitu.oop3.components.reservation;

import edu.aitu.oop3.components.reporting.ListResult;
import edu.aitu.oop3.components.monitoring.ParkingSpot;
import edu.aitu.oop3.components.common.Vehicle;

import java.util.Date;
import java.util.List;

public interface IReservationService {
    List<ParkingSpot> findFreeSpots();
    ListResult<ParkingSpot> getAllSpotsAsListResult();
    Reservation reserveSpot(ParkingSpot spot, Vehicle vehicle, Date startDate);
    void releaseSpot(Reservation reservation);
}
