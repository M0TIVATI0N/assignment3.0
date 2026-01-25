package edu.aitu.oop3.repositories;

import edu.aitu.oop3.data.IDB;
import edu.aitu.oop3.models.ParkingSpot;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import edu.aitu.oop3.models.Tariff;

public class ParkingSpotRepository implements IParkingSpotRepository {
    private final IDB db;

    public ParkingSpotRepository(IDB db) {
        this.db = db;
    }

    @Override
    public List<ParkingSpot> findFree() {
            List<ParkingSpot> freeSpots = new ArrayList<>();
            String sql = "SELECT ps.id, ps.is_reserved, t.id as t_id, t.name, t.cost " +
                    "FROM parking_spot ps " +
                    "JOIN tariff t ON ps.tariff_id = t.id " +
                    "WHERE ps.is_reserved = false";

            try (Connection conn = db.getConnection();
                 Statement st = conn.createStatement();
                 ResultSet rs = st.executeQuery(sql)) {

                    while (rs.next()) {
                            Tariff tariff = new Tariff(
                                    rs.getInt("t_id"),
                                    rs.getString("name"),
                                    rs.getInt("cost")
                                    );

                            freeSpots.add(new ParkingSpot(
                                    rs.getInt("id"),
                                    tariff,
                                    rs.getBoolean("is_reserved")
                            ));
                    }
            } catch (SQLException e) {
                    System.out.println("Error while searching for available seats: " + e.getMessage());
            }
            return freeSpots;
    }

    @Override
    public void updateStatus(int id, boolean isReserved) {
            String sql = "UPDATE parking_spot SET is_reserved = ? WHERE id = ?";

            try (Connection conn = db.getConnection();
                 PreparedStatement st = conn.prepareStatement(sql)) {

                    st.setBoolean(1, isReserved);
                    st.setInt(2, id);
                    st.executeUpdate();

            } catch (SQLException e) {
                    System.out.println("Error updating location status: " + e.getMessage());
            }
    }

    @Override
    public ParkingSpot getById(int id) {
            String sql = "SELECT ps.id, ps.is_reserved, t.id as t_id, t.name, t.cost " +
                    "FROM parking_spot ps " +
                    "JOIN tariff t ON ps.tariff_id = t.id " +
                    "WHERE ps.id = ?";

            try (Connection conn = db.getConnection();
                 PreparedStatement st = conn.prepareStatement(sql)) {

                    st.setInt(1, id);
                    try (ResultSet rs = st.executeQuery()) {
                            if (rs.next()) {
                                    Tariff tariff = new Tariff(
                                            rs.getInt("t_id"),
                                            rs.getString("name"),
                                            rs.getInt("cost")
                                    );
                                    return new ParkingSpot(rs.getInt("id"), tariff, rs.getBoolean("is_reserved"));
                            }
                    }
            } catch (SQLException e) {
                    System.out.println("getById error: " + e.getMessage());
            }
            return null;
    }
}
