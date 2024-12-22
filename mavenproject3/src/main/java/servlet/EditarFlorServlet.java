package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.Flor;
import service.FlorService;

@WebServlet("/editarFlor")
public class EditarFlorServlet extends HttpServlet {
    private final FlorService florService = new FlorService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Flor flor = florService.obtenerFlorPorId(id);

        if (flor != null) {
            request.setAttribute("flor", flor);
            request.getRequestDispatcher("editarFlor.jsp").forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Flor no encontrada");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String modelo = request.getParameter("modelo");
        String marca = request.getParameter("marca");
        int tamanio = Integer.parseInt(request.getParameter("tamanio"));
        String color = request.getParameter("color");
        double precioCosto = Double.parseDouble(request.getParameter("precio_costo"));
        int cantidad = Integer.parseInt(request.getParameter("cantidad"));

        Flor flor = new Flor(id, modelo, marca, tamanio, color, precioCosto, 0, cantidad);

        if (florService.actualizarFlor(flor)) {
            response.sendRedirect("listarFlores.jsp");
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al actualizar la flor");
        }
    }
}
