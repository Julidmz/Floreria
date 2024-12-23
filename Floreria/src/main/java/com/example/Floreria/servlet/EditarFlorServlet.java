package com.example.Floreria.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.example.Floreria.model.Flor;
import com.example.Floreria.service.FlorService;

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
        // Obtener los parámetros del formulario
        int id = Integer.parseInt(request.getParameter("id"));
        String nombreFlor = request.getParameter("nombreFlor");  // Aquí se ajusta a 'nombreFlor'
        int cantidadVaras = Integer.parseInt(request.getParameter("cantidadVaras"));  // Ajuste de cantidadVaras
        double precioUnitario = Double.parseDouble(request.getParameter("precioUnitario"));  // Ajuste de precioUnitario

        // Crear el objeto Flor con los valores obtenidos del formulario
        Flor flor = new Flor(id, nombreFlor, cantidadVaras, precioUnitario);

        // Actualizar la flor a través del servicio
        if (florService.actualizarFlor(flor)) {
            response.sendRedirect("listarFlores.jsp");
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al actualizar la flor");
        }
    }
}

