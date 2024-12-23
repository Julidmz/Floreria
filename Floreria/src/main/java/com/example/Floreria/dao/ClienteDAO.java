package com.example.Floreria.dao;

import com.example.Floreria.model.Cliente;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ClienteDAO {

    private final JdbcTemplate jdbcTemplate;

    public ClienteDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Método para obtener todos los clientes
    public List<Cliente> obtenerClientes() {
        String sql = "SELECT * FROM clientes";
        return jdbcTemplate.query(sql, new ClienteRowMapper());
    }

    // Método para insertar un cliente
    public boolean insertarCliente(String nombre, String apellido, String direccion, String telefono, String dni, String correo) {
        String sql = "INSERT INTO clientes (nombre, apellido, direccion, telefono, dni, correo) VALUES (?, ?, ?, ?, ?, ?)";
        int rowsAffected = jdbcTemplate.update(sql, nombre, apellido, direccion, telefono, dni, correo);
        return rowsAffected > 0;
    }

    // Método para eliminar un cliente
    public boolean eliminarCliente(int id) {
        String sql = "DELETE FROM clientes WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql, id);
        return rowsAffected > 0;
    }

    // Método para obtener un cliente por su ID
    public Cliente obtenerClientePorId(int id) {
        String sql = "SELECT * FROM clientes WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new ClienteRowMapper(), id);
    }

    // Método para actualizar un cliente
    public boolean actualizarCliente(Cliente cliente) {
        String sql = "UPDATE clientes SET nombre = ?, apellido = ?, direccion = ?, telefono = ?, dni = ?, correo = ? WHERE id = ?";
        int rowsAffected = jdbcTemplate.update(sql,
                cliente.getNombre(), cliente.getApellido(), cliente.getDireccion(),
                cliente.getTelefono(), cliente.getDni(), cliente.getEmail(),
                cliente.getId());
        return rowsAffected > 0;
    }

    // RowMapper para mapear los resultados a la clase Cliente
    private static class ClienteRowMapper implements RowMapper<Cliente> {
        @Override
        public Cliente mapRow(@NonNull ResultSet rs, int rowNum) throws SQLException {
            return new Cliente(
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
}

