package dao;

import model.Cliente;
import Utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteDAO {

    // Método para obtener todos los clientes
    public List<Cliente> obtenerClientes() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            String sql = "SELECT * FROM clientes";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String direccion = rs.getString("direccion");
                String telefono = rs.getString("telefono");
                String dni = rs.getString("dni");
                String correo = rs.getString("correo");

                Cliente cliente = new Cliente(id, nombre, apellido, direccion, telefono, dni, correo);
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            throw e;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }
        return clientes;
    }

    public boolean insertarCliente(String nombre, String apellido, String direccion, String telefono, String dni, String correo) throws SQLException, ClassNotFoundException {
    String sql = "INSERT INTO clientes (nombre, apellido, direccion, telefono, dni, correo) VALUES (?, ?, ?, ?, ?, ?)";
    try (Connection conn = DBConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        // Establecer los valores de la sentencia SQL
        stmt.setString(1, nombre);
        stmt.setString(2, apellido);
        stmt.setString(3, direccion);
        stmt.setString(4, telefono);
        stmt.setString(5, dni);
        stmt.setString(6, correo);

        // Ejecutar la sentencia de inserción
        int rowsAffected = stmt.executeUpdate();

        // Verificar si la inserción fue exitosa
        return rowsAffected > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}


    // Método para eliminar un cliente
    public boolean eliminarCliente(int id) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM clientes WHERE id = ?";
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

    // Método para obtener un cliente por su ID
    public Cliente obtenerClientePorId(int id) {
        Cliente cliente = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM clientes WHERE id = ?")) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cliente = new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("apellido"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("dni"),
                        rs.getString("correo")
                    );
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return cliente;
    }

    // Método para actualizar un cliente
    public boolean actualizarCliente(Cliente cliente) {
        boolean actualizado = false;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                 "UPDATE clientes SET nombre = ?, apellido = ?, direccion = ?, telefono = ?, dni = ?, correo = ? WHERE id = ?")) {

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getDireccion());
            ps.setString(4, cliente.getTelefono());
            ps.setString(5, cliente.getDni());
            ps.setString(6, cliente.getCorreo());
            ps.setInt(7, cliente.getId());

            actualizado = ps.executeUpdate() > 0;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return actualizado;
    }
}
