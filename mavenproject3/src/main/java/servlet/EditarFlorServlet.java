package servlet;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.Zapato;
import service.ZapatoService;

@WebServlet("/editarFlor")
public class EditarZapatoServlet extends HttpServlet {
    private final ZapatoService zapatoService = new ZapatoService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Zapato zapato = zapatoService.obtenerZapatoPorId(id);

        if (zapato != null) {
            request.setAttribute("zapato", zapato);
            request.getRequestDispatcher("editarZapato.jsp").forward(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Zapato no encontrado");
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

        Zapato zapato = new Zapato(id, modelo, marca, tamanio, color, precioCosto, 0, cantidad);

        if (zapatoService.actualizarZapato(zapato)) {
            response.sendRedirect("listarZapatos.jsp");
        } else {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al actualizar el zapato");
        }
    }
}
