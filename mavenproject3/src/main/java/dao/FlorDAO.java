package dao;

import java.sql.PreparedStatement;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Flor;
import Utils.DBConnection;
import java.util.logging.Level;
import java.util.logging.Logger;



public class FlorDAO {

    public List<Flor> obtenerFlor() throws SQLException {
        List<Flor> flor = new ArrayList<>();
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
                String modelo = rs.getString("modelo");
                String marca = rs.getString("marca");
                int talle = rs.getInt("talle");
                String color = rs.getString("color");
                double precioCosto = rs.getDouble("precio_costo");
                double precioVenta = rs.getDouble("precio_venta");
                int cantidad = rs.getInt("cantidad");

                Flor flor = new Flor(id, modelo, marca, talle, color, precioCosto, precioVenta, cantidad);
                flor.add(flor);
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
        return flor;
    }
    
        public boolean insertarFlor(String modelo, String marca, int talle, String color, double precioCosto, double precioVenta, int cantidad) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO flor (modelo, marca, talle, color, precio_costo, precio_venta, cantidad) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Establecer los valores de la sentencia SQL
            stmt.setString(1, modelo);
            stmt.setString(2, marca);
            stmt.setInt(3, talle);
            stmt.setString(4, color);
            stmt.setDouble(5, precioCosto);
            stmt.setDouble(6, precioVenta);
            stmt.setInt(7, cantidad);

            // Ejecutar la sentencia de inserción
            int rowsAffected = stmt.executeUpdate();

            // Verificar si la inserción fue exitosa
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
        
        // Establecer el ID en la consulta
        stmt.setInt(1, id);

        // Ejecutar la sentencia DELETE
        int rowsAffected = stmt.executeUpdate();

        // Verificar si se eliminó algún registro
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
                        rs.getString("modelo"),
                        rs.getString("marca"),
                        rs.getInt("talle"),
                        rs.getString("color"),
                        rs.getDouble("precio_costo"),
                        rs.getDouble("precio_venta"),
                        rs.getInt("cantidad")
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
                 "UPDATE flor SET modelo = ?, marca = ?, talle = ?, color = ?, precio_costo = ?, precio_venta = ?, cantidad = ? WHERE id = ?")) {

            ps.setString(1, flor.getModelo());
            ps.setString(2, flor.getMarca());
            ps.setInt(3, flor.getTalle());
            ps.setString(4, flor.getColor());
            ps.setDouble(5, flor.getPrecioCosto());
            ps.setDouble(6, flor.getPrecioVenta());
            ps.setInt(7, flor.getCantidad());
            ps.setInt(8, flor.getId());

            actualizado = ps.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return actualizado;
    }
    
}
