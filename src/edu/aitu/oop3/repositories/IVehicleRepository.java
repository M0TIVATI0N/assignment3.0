package edu.aitu.oop3.repositories;
import edu.aitu.oop3.models.Vehicle;

public interface IVehicleRepository {
    Vehicle findByPlate(String plate);
    Vehicle add(Vehicle vehicle);
}
