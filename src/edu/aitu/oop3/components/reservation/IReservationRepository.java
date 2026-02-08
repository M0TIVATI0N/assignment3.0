package edu.aitu.oop3.components.reservation;

public interface IReservationRepository {
    Reservation findById(int id);
    Reservation addReservation(Reservation reservation);
    void releaseReservation(int reservationId);

}
