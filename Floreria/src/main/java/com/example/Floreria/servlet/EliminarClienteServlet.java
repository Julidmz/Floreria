package com.example.Floreria.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.example.Floreria.service.ClienteService;
import com.example.Floreria.dao.ClienteDAO;
import org.springframework.jdbc.core.JdbcTemplate;

import java.io.IOException;
import java.sql.SQLException;  // Importar SQLException
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/eliminarCliente")
public class EliminarClienteServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(EliminarClienteServlet.class.getName());  // Logger

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Obtener el ID del cliente a eliminar
            int id = Integer.parseInt(request.getParameter("id"));

            // Crear una instancia de JdbcTemplate (configurado en Spring o manualmente)
            JdbcTemplate jdbcTemplate = new JdbcTemplate();  // Asegúrate de configurarlo correctamente con un DataSource

            // Crear una instancia de ClienteDAO y ClienteService
            ClienteDAO clienteDAO = new ClienteDAO(jdbcTemplate);  // Pasar JdbcTemplate al constructor de ClienteDAO
            ClienteService clienteService = new ClienteService(clienteDAO); // Pasar ClienteDAO al constructor de ClienteService

            // Intentar eliminar el cliente de la base de datos
            boolean success = clienteService.eliminarCliente(id);

            // Redirigir al listado después de eliminar
            if (success) {
                response.sendRedirect("listarClientes.jsp?message=Cliente eliminado exitosamente");
            } else {
                response.sendRedirect("listarClientes.jsp?error=No se pudo eliminar el cliente");
            }
        } catch (NumberFormatException e) {
            // Captura de error al parsear el ID
            logger.log(Level.SEVERE, "Error al parsear el ID del cliente", e);
            response.sendRedirect("listarClientes.jsp?error=ID de cliente no válido");
        } catch (SQLException e) {  // Captura SQLException
            // Manejo de excepciones de base de datos
            logger.log(Level.SEVERE, "Error al eliminar el cliente de la base de datos", e);
            response.sendRedirect("listarClientes.jsp?error=Error al procesar la solicitud");
        } catch (Exception e) {
            // Manejo de otras excepciones usando logger
            logger.log(Level.SEVERE, "Error al procesar la solicitud", e);
            response.sendRedirect("listarClientes.jsp?error=Error al procesar la solicitud");
        }
    }
}

