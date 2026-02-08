package edu.aitu.oop3.components.payment;

import edu.aitu.oop3.components.reservation.Reservation;
import edu.aitu.oop3.components.reservation.IReservationRepository;

public class PricingService implements IPricingService {
    public final IReservationRepository reservationRepository;
    public final ITariffRepository tariffRepository;

    public PricingService(IReservationRepository reservationRepository, ITariffRepository tariffRepository) {
        this.reservationRepository = reservationRepository;
        this.tariffRepository = tariffRepository;
    }

    @Override
    public int calculateCost(Reservation reservation) {
        Reservation finishedReservation = reservationRepository.findById(reservation.getId());

        if (finishedReservation == null || finishedReservation.getTo() == null) {
            return 0;
        }

        // Reservation -> Spot -> Tariff
        Tariff tariff = tariffRepository.getById(finishedReservation.getSpot().getTariff().getId());

        long start = finishedReservation.getFrom().getTime();
        long end = finishedReservation.getTo().getTime();
        long diffInMs = end - start;

        double hours = diffInMs / (1000.0 * 60 * 60);
        int finalHours = (int) Math.ceil(hours);

        if (finalHours == 0) finalHours = 1;

        return tariff.getCost() * finalHours;
    }
}

