package edu.aitu.oop3.repositories;
import edu.aitu.oop3.models.Reservation;

public interface IReservationRepository {
    Reservation findById(int id);
    Reservation addReservation(Reservation reservation);
    void releaseReservation(int reservationId);

}
