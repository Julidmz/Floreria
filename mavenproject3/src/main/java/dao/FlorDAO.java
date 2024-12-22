package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Flor;
import Utils.DBConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FlorDAO {

    public List<Flor> obtenerFlores() throws SQLException {
        List<Flor> flores = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT * FROM flor";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombreFlor = rs.getString("nombre_flor");
                int cantidadVaras = rs.getInt("cantidad_varas");
                double precioUnitario = rs.getDouble("precio_unitario");

                Flor flor = new Flor(id, nombreFlor, cantidadVaras, precioUnitario);
                flores.add(flor);
            }
        } catch (SQLException e) {
            throw e;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(FlorDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }
        return flores;
    }

    public boolean insertarFlor(Flor flor) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO flor (nombre_flor, cantidad_varas, precio_unitario) VALUES (?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, flor.getNombreFlor());
            stmt.setInt(2, flor.getCantidadVaras());
            stmt.setDouble(3, flor.getPrecioUnitario());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminarFlor(int id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM flor WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Flor obtenerFlorPorId(int id) {
        Flor flor = null;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM flor WHERE id = ?")) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    flor = new Flor(
                        rs.getInt("id"),
                        rs.getString("nombre_flor"),
                        rs.getInt("cantidad_varas"),
                        rs.getDouble("precio_unitario")
                    );
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return flor;
    }

    public boolean actualizarFlor(Flor flor) {
        boolean actualizado = false;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                 "UPDATE flor SET nombre_flor = ?, cantidad_varas = ?, precio_unitario = ? WHERE id = ?")) {

            ps.setString(1, flor.getNombreFlor());
            ps.setInt(2, flor.getCantidadVaras());
            ps.setDouble(3, flor.getPrecioUnitario());
            ps.setInt(4, flor.getId());

            actualizado = ps.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return actualizado;
    }
}