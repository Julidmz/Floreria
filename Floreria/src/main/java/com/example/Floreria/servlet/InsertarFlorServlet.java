package com.example.Floreria.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.example.Floreria.service.FlorService;
import com.example.Floreria.model.Flor;

import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/insertarFlor")
public class InsertarFlorServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(InsertarFlorServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Obtener los parámetros del formulario
            String nombreFlor = request.getParameter("nombreFlor");
            int cantidadVaras = Integer.parseInt(request.getParameter("cantidadVaras"));
            double precioUnitario = Double.parseDouble(request.getParameter("precioUnitario"));
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));

            // Crear una instancia de Flor
            Flor flor = new Flor();
            flor.setNombre(nombreFlor);
            flor.setCantidadVaras(cantidadVaras);
            flor.setPrecioUnitario(precioUnitario);
            flor.setCantidad(cantidad);

            // Crear una instancia de FlorService
            FlorService florService = new FlorService();

            // Intentar insertar la flor en la base de datos
            boolean success = florService.insertarFlor(flor);

            // Enviar respuesta al usuario
            if (success) {
                response.getWriter().write("Flor agregada exitosamente.");
            } else {
                response.getWriter().write("Error al agregar flor.");
            }
        } catch (Exception e) {
            // Manejo de excepciones generales
            logger.severe("Error inesperado: " + e.getMessage());
            response.getWriter().write("Error inesperado.");
        }
    }
}


