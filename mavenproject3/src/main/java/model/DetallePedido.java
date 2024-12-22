package model;

import java.math.BigDecimal;

public class DetallePedido {
    private int id;
    private int pedidoId;
    private int florId;
    private Flor flor; // Para facilitar mostrar datos del zapato
    private int cantidad;
    private BigDecimal precioUnitario;

    // Constructores
    public DetallePedido() {
    }

    public DetallePedido(int id, int pedidoId, int florId, int cantidad, BigDecimal precioUnitario) {
        this.id = id;
        this.pedidoId = pedidoId;
        this.florId = florId;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(int pedidoId) {
        this.pedidoId = pedidoId;
    }

    public int getFlorId() {
        return florId;
    }

    public void setFlorId(int zapatoId) {
        this.florId = zapatoId;
    }

    public Flor getFlor() {
        return flor;
    }

    public void setFlor(Flor flor) {
        this.flor= flor;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
}
