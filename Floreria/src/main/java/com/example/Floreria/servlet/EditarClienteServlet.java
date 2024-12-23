package com.example.Floreria.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.example.Floreria.model.Cliente;
import com.example.Floreria.service.ClienteService;
import com.example.Floreria.dao.ClienteDAO;  // Importa la clase ClienteDAO

@WebServlet("/editarCliente")
public class EditarClienteServlet extends HttpServlet {
    // Crea una instancia de ClienteDAO y luego pasa esa instancia al ClienteService
    private final ClienteDAO clienteDAO = new ClienteDAO(null);  // Crea una instancia de ClienteDAO
    private final ClienteService clienteService = new ClienteService(clienteDAO);  // Pasa el clienteDAO al ClienteService

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener el ID del cliente que se desea editar
        int id = Integer.parseInt(request.getParameter("id"));
        
        // Obtener el cliente desde el servicio
        Cliente cliente = clienteService.obtenerClientePorId(id);

        // Si el cliente existe, pasarlo al JSP para su edición
        if (cliente != null) {
            request.setAttribute("cliente", cliente);
            request.getRequestDispatcher("editarCliente.jsp").forward(request, response);
        } else {
            // Si el cliente no existe, enviar un error
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Cliente no encontrado");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtener los parámetros del formulario de edición
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String direccion = request.getParameter("direccion");
        String telefono = request.getParameter("telefono");
        String dni = request.getParameter("dni");
        String email = request.getParameter("correo");

        // Crear un objeto Cliente con los nuevos datos
        Cliente cliente = new Cliente(id, nombre, apellido, direccion, telefono, dni, email);

        // Llamar al servicio para actualizar el cliente
        if (clienteService.actualizarCliente(cliente)) {
            // Si la actualización fue exitosa, redirigir al listado de clientes
            response.sendRedirect("listarClientes.jsp");
        } else {
            // Si hubo un error, enviar un mensaje de error
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al actualizar el cliente");
        }
    }
}

