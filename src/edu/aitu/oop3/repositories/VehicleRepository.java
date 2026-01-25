package edu.aitu.oop3.repositories;

import edu.aitu.oop3.data.IDB;
import edu.aitu.oop3.models.Vehicle;

import java.sql.*;

public class VehicleRepository implements IVehicleRepository {
    private final IDB db;

    public VehicleRepository(IDB db) {
        this.db = db;
    }

    @Override
    public Vehicle findByPlate(String plate) {
        String sql = "SELECT id, licensePlate FROM vehicles WHERE licensePlate = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {

            st.setString(1, plate);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {

                return new Vehicle(rs.getInt("id"), rs.getString("licensePlate"));
            }
        } catch (SQLException e) {
            System.out.println("Error searching for car: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Vehicle add(Vehicle vehicle) {

        String sql = "INSERT INTO vehicles(licensePlate) VALUES (?) RETURNING id";

        try (Connection conn = db.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {

            st.setString(1, vehicle.getLicensePlate());
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return new Vehicle(rs.getInt("id"), vehicle.getLicensePlate());
            }
        } catch (SQLException e) {
            System.out.println("Error adding vehicle: " + e.getMessage());
        }
        return null;
    }

}