package edu.aitu.oop3.components.payment;

import edu.aitu.oop3.components.reservation.Reservation;

public interface IPricingService{
    int calculateCost(Reservation reservation2);
}
