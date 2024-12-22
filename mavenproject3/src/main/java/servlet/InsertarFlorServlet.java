package servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.FlorService;


import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/insertarFlor")
public class InsertarFlorServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Obtener los parámetros del formulario
            String modelo = request.getParameter("modelo");
            String marca = request.getParameter("marca");
            int talle = Integer.parseInt(request.getParameter("talle"));
            String color = request.getParameter("color");
            double precioCosto = Double.parseDouble(request.getParameter("precio_costo"));
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));
            
            // Crear una instancia de ZapatoService
            FlorService florService = new FlorService();
            
            // Intentar agregar el zapato a la base de datos
            boolean success = florService.agregarFlor(modelo, marca, talle, color, precioCosto, cantidad);

            // Enviar respuesta al usuario
            if (success) {
                response.getWriter().write("Flor agregadada exitosamente.");
            } else {
                response.getWriter().write("Error al agregar flor.");
            }
        } catch (SQLException e) {
            // Aquí manejas la excepción SQLException
            e.printStackTrace();
            response.getWriter().write("Error en la base de datos: " + e.getMessage());
        } catch (Exception e) {
            // Manejo de otras excepciones
            e.printStackTrace();
            response.getWriter().write("Error inesperado: " + e.getMessage());
        }
    }
}
