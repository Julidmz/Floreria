package com.example.Floreria.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.example.Floreria.service.ClienteService;
import com.example.Floreria.dao.ClienteDAO;  // Asegúrate de importar ClienteDAO
import org.springframework.jdbc.core.JdbcTemplate;  // Importar JdbcTemplate

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/insertarCliente")
public class InsertarClienteServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(InsertarClienteServlet.class.getName());  // Logger

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Obtener los parámetros del formulario
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String direccion = request.getParameter("direccion");
            String telefono = request.getParameter("telefono");
            String dni = request.getParameter("dni");
            String correo = request.getParameter("correo");

            // Crear una instancia de JdbcTemplate (configurado en Spring o manualmente)
            JdbcTemplate jdbcTemplate = new JdbcTemplate();  // Asegúrate de configurarlo correctamente con un DataSource

            // Crear una instancia de ClienteDAO y ClienteService
            ClienteDAO clienteDAO = new ClienteDAO(jdbcTemplate);  // Pasar JdbcTemplate al constructor de ClienteDAO
            ClienteService clienteService = new ClienteService(clienteDAO); // Pasar ClienteDAO al constructor de ClienteService

            // Intentar agregar el cliente a la base de datos
            boolean success = clienteService.agregarCliente(nombre, apellido, direccion, telefono, dni, correo);

            // Enviar respuesta al usuario
            if (success) {
                response.getWriter().write("Cliente agregado exitosamente.");
            } else {
                response.getWriter().write("Error DNI o EMAIL del cliente existente.");
            }
        } catch (SQLException | IOException e) {  // Multicatch
            // Manejo de las excepciones SQLException e IOException
            logger.log(Level.SEVERE, "Error en la base de datos", e);
            response.getWriter().write("Error en la base de datos: " + e.getMessage());
        } catch (Exception e) {
            // Manejo de otras excepciones usando logger
            logger.log(Level.SEVERE, "Error inesperado", e);
            response.getWriter().write("Error inesperado: " + e.getMessage());
        }
    }
}


