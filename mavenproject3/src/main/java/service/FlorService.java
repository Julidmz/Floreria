package service;

import dao.FlorDAO;
import java.sql.SQLException;
import model.Flor;

public class FlorService {

    private final FlorDAO florDAO;

    public FlorService() {
        this.florDAO = new FlorDAO();
    }

    public boolean agregarFlor(String modelo, String marca, int talle, String color, double precioCosto, int cantidad) throws SQLException, ClassNotFoundException {
        // Calcular el precio de venta como un 20% adicional al costo
        double precioVenta = precioCosto * 1.20;

        // Insertar flor en la base de datos usando el DAO
        return florDAO.insertarFlor(modelo, marca, talle, color, precioCosto, precioVenta, cantidad);
    }
    
    public boolean eliminarFlor(int id) throws Exception {
    return florDAO.eliminarFlor(id);
}
      public Flor obtenerFlorPorId(int id) {
        return florDAO.obtenerFlorPorId(id);
    }

    public boolean actualizarZapato(Flor flor) {
        // Calcula el precio de venta como un 20% adicional al costo
        flor.setPrecioVenta(flor.getPrecioCosto() * 1.20);
        return florDAO.actualizarFlor(flor);
    }
}
