package model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Pedido {
    private int id;
    private int clienteId;
    private Cliente cliente; // Para facilitar mostrar datos del cliente
    private Date fecha;
    private BigDecimal total;
    private List<DetallePedido> detalles; // Lista de detalles del pedido

    // Constructores
    public Pedido() {
    }

    public Pedido(int id, int clienteId, Date fecha, BigDecimal total) {
        this.id = id;
        this.clienteId = clienteId;
        this.fecha = fecha;
        this.total = total;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<DetallePedido> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePedido> detalles) {
        this.detalles = detalles;
    }
}
