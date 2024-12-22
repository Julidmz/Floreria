package service;

import dao.FlorDAO;
import java.sql.SQLException;
import model.Flor;

public class FlorService {

    private final FlorDAO florDAO;

    public FlorService() {
        this.florDAO = new FlorDAO();
    }

    public boolean agregarFlor(String nombreFlor, int cantidadVaras, double precioUnitario, int cantidad) throws SQLException, ClassNotFoundException {
        // Calcular el precio de venta como un 20% adicional al precio unitario
        double precioVenta = precioUnitario * 1.20;

        // Crear la instancia de Flor con los parámetros correctos
        Flor flor = new Flor();
        flor.setNombreFlor(nombreFlor);
        flor.setCantidadVaras(cantidadVaras);
        flor.setPrecioUnitario(precioUnitario);

        // Insertar flor en la base de datos usando el DAO
        return florDAO.insertarFlor(flor, precioVenta, cantidad);
    }

    public boolean eliminarFlor(int id) throws Exception {
        return florDAO.eliminarFlor(id);
    }

    public Flor obtenerFlorPorId(int id) {
        return florDAO.obtenerFlorPorId(id);
    }

    public boolean actualizarFlor(Flor flor) {
        // Calcula el precio de venta como un 20% adicional al precio unitario
        double precioVenta = flor.getPrecioUnitario() * 1.20;
        flor.setPrecioVenta(precioVenta);

        // Actualiza la flor en la base de datos usando el DAO
        return florDAO.actualizarFlor(flor);
    }
}

