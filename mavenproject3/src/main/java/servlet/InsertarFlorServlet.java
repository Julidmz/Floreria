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
            String nombreFlor = request.getParameter("nombreFlor");
            int cantidadVaras = Integer.parseInt(request.getParameter("cantidadVaras"));
            double precioUnitario = Double.parseDouble(request.getParameter("precioUnitario"));
            int cantidad = Integer.parseInt(request.getParameter("cantidad"));
            
            // Crear una instancia de FlorService
            FlorService florService = new FlorService();
            
            // Intentar agregar la flor a la base de datos
            boolean success = florService.agregarFlor(nombreFlor, cantidadVaras, precioUnitario, cantidad);

            // Enviar respuesta al usuario
            if (success) {
                response.getWriter().write("Flor agregada exitosamente.");
            } else {
                response.getWriter().write("Error al agregar flor.");
            }
        } catch (SQLException e) {
            // Manejo de excepción SQLException
            e.printStackTrace();
            response.getWriter().write("Error en la base de datos: " + e.getMessage());
        } catch (Exception e) {
            // Manejo de otras excepciones
            e.printStackTrace();
            response.getWriter().write("Error inesperado: " + e.getMessage());
        }
    }
}

