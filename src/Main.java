import edu.aitu.oop3.data.*;
import edu.aitu.oop3.repositories.*;
import edu.aitu.oop3.services.*;
import edu.aitu.oop3.models.*;
import edu.aitu.oop3.exceptions.*;

import java.util.Scanner;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        IDB db = new PostgresDB();

        IParkingSpotRepository spotRepo = new ParkingSpotRepository(db);
        IReservationRepository resRepo = new ReservationRepository(db);
        ITariffRepository tariffRepo = new TariffRepository(db);
        IVehicleRepository vehicleRepo = new VehicleRepository(db);

        IReservationService reservationService = new ReservationService(spotRepo, resRepo, vehicleRepo);
        IPricingService pricingService = new PricingService(resRepo, tariffRepo);

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Show free parking spots");
            System.out.println("2. Reserve a parking spot");
            System.out.println("3. Leave and pay");
            System.out.println("4. Show all tariffs");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            int choice = -1;
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            try {
                switch (choice) {
                    case 1:
                        showFreeSpots(reservationService);
                        break;
                    case 2:
                        reserveSpot(scanner, reservationService, spotRepo);
                        break;
                    case 3:
                        releaseAndPay(scanner, reservationService, pricingService, resRepo, spotRepo);
                        break;
                    case 4:
                        showAllTariffs(tariffRepo);
                        break;
                    case 0:
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } catch (NoFreeSpotsException e) {
                System.out.println(e.getMessage());
            } catch (InvalidVehiclePlateException e) {
                System.out.println(e.getMessage());
            } catch (ReservationStatusException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private static void showFreeSpots(IReservationService reservationService) {
        List<ParkingSpot> freeSpots = reservationService.findFreeSpots();
        if (freeSpots.isEmpty()) {
            System.out.println("No free parking spots available.");
        } else {
            System.out.println("Available parking spots:");
            System.out.printf("%-4s %-12s %-8s%n", "ID", "Tariff", "Price/h");
            for (ParkingSpot spot : freeSpots) {
                System.out.printf("%-4d %-12s %-8d%n",
                        spot.getId(),
                        spot.getTariff().getName(),
                        spot.getTariff().getCost());
            }
        }
    }

    private static void reserveSpot(Scanner scanner, IReservationService reservationService,
                                    IParkingSpotRepository spotRepo) {
        System.out.print("Enter vehicle plate number: ");
        String plate = scanner.nextLine().trim();

        if (plate.isEmpty()) {
            System.out.println("Vehicle plate number cannot be empty.");
            return;
        }

        System.out.print("Enter parking spot ID: ");
        int spotId = scanner.nextInt();
        scanner.nextLine();

        Vehicle vehicle = new Vehicle(0, plate);
        ParkingSpot spot = spotRepo.getById(spotId);

        if (spot == null) {
            System.out.println("Parking spot with ID " + spotId + " not found.");
        } else if (spot.isReserved()) {
            System.out.println("Parking spot is already reserved.");
        } else {
            Reservation res = reservationService.reserveSpot(spot, vehicle, new Date());
            System.out.println(
                    "Reservation created. ID: " + res.getId() +
                            ", Spot: " + spot.getId() +
                            ", Tariff: " + spot.getTariff().getName()
            );
        }
    }

    private static void releaseAndPay(Scanner scanner, IReservationService reservationService,
                                      IPricingService pricingService, IReservationRepository resRepo,
                                      IParkingSpotRepository spotRepo) {
        System.out.print("Enter reservation ID: ");
        int resId = scanner.nextInt();
        scanner.nextLine();

        Reservation existingRes = resRepo.findById(resId);
        if (existingRes == null) {
            System.out.println("Reservation with ID " + resId + " not found.");
            return;
        }

        if (existingRes.getTo() != null) {
            System.out.println("This reservation is already closed.");
            return;
        }

        reservationService.releaseSpot(existingRes);
        spotRepo.updateStatus(existingRes.getSpot().getId(), false);

        int cost = pricingService.calculateCost(existingRes);

        System.out.println("Parking finished. Total cost: " + cost);
    }

    private static void showAllTariffs(ITariffRepository tariffRepo) {
        List<Tariff> tariffs = tariffRepo.getAllTariffs();
        if (tariffs.isEmpty()) {
            System.out.println("No tariffs found.");
        } else {
            System.out.println("Available tariffs:");
            System.out.printf("%-4s %-12s %-8s%n", "ID", "Name", "Price/h");
            for (Tariff t : tariffs) {
                System.out.printf("%-4d %-12s %-8d%n",
                        t.getId(), t.getName(), t.getCost());
            }
        }
    }
}
