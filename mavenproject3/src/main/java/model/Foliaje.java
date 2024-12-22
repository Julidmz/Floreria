package model;

public class Foliaje {
    private int id;
    private String nombreFoliaje;
    private int cantidad;
    private double precioUnitario;

    // Constructor
    public Foliaje(int id, String nombreFoliaje, int cantidad, double precioUnitario) {
        this.id = id;
        this.nombreFoliaje = nombreFoliaje;
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

    public String getNombreFoliaje() {
        return nombreFoliaje;
    }

    public void setNombreFoliaje(String nombreFoliaje) {
        this.nombreFoliaje = nombreFoliaje;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    // Métodos
    public void actualizarStock(int cantidad) {
        this.cantidad += cantidad;
    }

    public double calcularPrecioTotal(int cantidad) {
        return cantidad * precioUnitario;
    }
}
