package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ZapatoService;

import java.io.IOException;

@WebServlet("/eliminarZapato")
public class EliminarZapatoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Obtener el ID del zapato a eliminar
            int id = Integer.parseInt(request.getParameter("id"));

            // Instanciar el servicio y eliminar el zapato
            ZapatoService zapatoService = new ZapatoService();
            boolean success = zapatoService.eliminarZapato(id);

            // Redirigir al listado después de eliminar
            if (success) {
                response.sendRedirect("listarZapatos.jsp?message=Zapato eliminado exitosamente");
            } else {
                response.sendRedirect("listarZapatos.jsp?error=No se pudo eliminar el zapato");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("listarZapatos.jsp?error=Error al procesar la solicitud");
        }
    }
}
