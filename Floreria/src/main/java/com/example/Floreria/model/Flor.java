package com.example.Floreria.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name = "flor")
public class Flor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombreFlor;
    private int cantidadVaras;
    private double precioVenta;  // Atributo de precioVenta

    // Constructor con parámetros
    public Flor(int id, String nombreFlor, int cantidadVaras, double precioVenta) {
        this.id = id;
        this.nombreFlor = nombreFlor;
        this.cantidadVaras = cantidadVaras;
        this.precioVenta = precioVenta;  // Establecemos precioVenta
    }

    // Constructor sin parámetros para JPA
    public Flor() {}

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreFlor() {
        return nombreFlor;
    }

    public void setNombreFlor(String nombreFlor) {
        if (nombreFlor != null && !nombreFlor.trim().isEmpty()) {
            this.nombreFlor = nombreFlor;
        } else {
            throw new IllegalArgumentException("El nombre de la flor no puede estar vacío.");
        }
    }

    public int getCantidadVaras() {
        return cantidadVaras;
    }

    public void setCantidadVaras(int cantidadVaras) {
        if (cantidadVaras >= 0) {
            this.cantidadVaras = cantidadVaras;
        } else {
            throw new IllegalArgumentException("La cantidad de varas no puede ser negativa.");
        }
    }

    public double getPrecioVenta() {  // Getter para precioVenta
        return precioVenta;
    }

    public void setPrecioVenta(double precioVenta) {  // Setter para precioVenta
        if (precioVenta >= 0) {
            this.precioVenta = precioVenta;
        } else {
            throw new IllegalArgumentException("El precio de venta no puede ser negativo.");
        }
    }

    // Métodos adicionales
    public void actualizarStock(int cantidad) {
        if (cantidad > 0) {
            this.cantidadVaras += cantidad;
        } else {
            throw new IllegalArgumentException("La cantidad a agregar debe ser positiva.");
        }
    }

    public double calcularPrecioTotal(int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor que 0.");
        }
        return cantidad * precioVenta;  // Usamos 'precioVenta' en lugar de 'precioUnitario'
    }

    @Override
    public String toString() {
        return "Flor [id=" + id + ", nombreFlor=" + nombreFlor + ", cantidadVaras=" + cantidadVaras + ", precioVenta=" + precioVenta + "]";
    }

    public void setCantidad(int cantidad) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setPrecioUnitario(double precioUnitario) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void setNombre(String nombreFlor) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}



