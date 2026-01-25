package edu.aitu.oop3.repositories;

import edu.aitu.oop3.data.IDB;
import edu.aitu.oop3.models.Tariff;

import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class TariffRepository implements ITariffRepository {
    private final IDB db;

    public TariffRepository(IDB db) {
        this.db = db;
    }

    @Override
    public Tariff getById(int id) {
        String sql = "SELECT * FROM tariff WHERE id = ?";
        try (Connection conn = db.getConnection();
             PreparedStatement st = conn.prepareStatement(sql)) {

            st.setInt(1, id);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                return new Tariff(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("cost")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error searching by ID: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Tariff> getAllTariffs() {
        List<Tariff> tariffs = new ArrayList<>();
        String sql = "SELECT * FROM tariff";

        try (Connection conn = db.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {
                tariffs.add(new Tariff(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("cost")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error getting all tariffs: " + e.getMessage());
        }
        return tariffs;
    }
}