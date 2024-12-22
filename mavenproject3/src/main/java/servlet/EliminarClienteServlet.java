package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ClienteService;

import java.io.IOException;

@WebServlet("/eliminarCliente")
public class EliminarClienteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Obtener el ID del cliente a eliminar
            int id = Integer.parseInt(request.getParameter("id"));

            // Instanciar el servicio y eliminar el cliente
            ClienteService clienteService = new ClienteService();
            boolean success = clienteService.eliminarCliente(id);

            // Redirigir al listado después de eliminar
            if (success) {
                response.sendRedirect("listarClientes.jsp?message=Cliente eliminado exitosamente");
            } else {
                response.sendRedirect("listarClientes.jsp?error=No se pudo eliminar el cliente");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("listarClientes.jsp?error=Error al procesar la solicitud");
        }
    }
}
