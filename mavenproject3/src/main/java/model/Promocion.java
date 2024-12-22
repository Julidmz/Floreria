package model;

public class Promocion {
    private int id;
    private Flor flor;
    private Foliaje foliaje;
    private double precioPromocion;
    private int cantidad;

    // Constructor
    public Promocion(int id, Flor flor, Foliaje foliaje, double precioPromocion, int cantidad) {
        this.id = id;
        this.flor = flor;
        this.foliaje = foliaje;
        this.precioPromocion = precioPromocion;
        this.cantidad = cantidad;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Flor getFlor() {
        return flor;
    }

    public void setFlor(Flor flor) {
        this.flor = flor;
    }

    public Foliaje getFoliaje() {
        return foliaje;
    }

    public void setFoliaje(Foliaje foliaje) {
        this.foliaje = foliaje;
    }

    public double getPrecioPromocion() {
        return precioPromocion;
    }

    public void setPrecioPromocion(double precioPromocion) {
        this.precioPromocion = precioPromocion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    // Métodos
    public double calcularPrecioTotal() {
        return cantidad * precioPromocion;
    }

    public void mostrarInformacion() {
        System.out.println("Promoción ID: " + id);
        System.out.println("Flor: " + flor.getNombreFlor());
        System.out.println("Foliaje: " + foliaje.getNombreFoliaje());
        System.out.println("Precio por promoción: $" + precioPromocion);
        System.out.println("Cantidad disponible: " + cantidad);
    }
}
