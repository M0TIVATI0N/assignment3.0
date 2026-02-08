package edu.aitu.oop3.components.monitoring;
import edu.aitu.oop3.components.common.Vehicle;

public interface IVehicleRepository {
    Vehicle findByPlate(String plate);
    Vehicle add(Vehicle vehicle);
}
