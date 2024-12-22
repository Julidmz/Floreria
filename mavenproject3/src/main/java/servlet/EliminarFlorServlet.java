package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.FlorService;

import java.io.IOException;

@WebServlet("/eliminarFlor")
public class EliminarFlorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Obtener el ID del zapato a eliminar
            int id = Integer.parseInt(request.getParameter("id"));

            // Instanciar el servicio y eliminar el zapato
            FlorService florService = new FlorService();
            boolean success = florService.eliminarFlor(id);

            // Redirigir al listado después de eliminar
            if (success) {
                response.sendRedirect("listarFlores.jsp?message=Flor eliminada exitosamente");
            } else {
                response.sendRedirect("listarFlores.jsp?error=No se pudo eliminar la flor");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("listarFlores.jsp?error=Error al procesar la solicitud");
        }
    }
}
