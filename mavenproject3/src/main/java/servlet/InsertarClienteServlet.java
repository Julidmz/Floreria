package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ClienteService;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/insertarCliente")
public class InsertarClienteServlet extends HttpServlet {

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

            // Crear una instancia de ClienteService
            ClienteService clienteService = new ClienteService();

            // Intentar agregar el cliente a la base de datos
            boolean success = clienteService.agregarCliente(nombre, apellido, direccion, telefono, dni, correo);

            // Enviar respuesta al usuario
            if (success) {
                response.getWriter().write("Cliente agregado exitosamente.");
            } else {
                response.getWriter().write("Error DNI o MEIL del cliente existente.");
            }
        } catch (SQLException e) {
            // Manejo de la excepción SQLException
            e.printStackTrace();
            response.getWriter().write("Error en la base de datos: " + e.getMessage());
        } catch (Exception e) {
            // Manejo de otras excepciones
            e.printStackTrace();
            response.getWriter().write("Error inesperado: " + e.getMessage());
        }
    }
}
