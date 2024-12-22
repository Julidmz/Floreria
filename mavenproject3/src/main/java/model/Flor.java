package model;

public class Flor {
    private int id;
    private String nombreFlor;
    private int cantidadVaras;
    private double precioUnitario;

    // Constructor con parámetros
    public Flor(int id, String nombreFlor, String marca, int cantidadVaras, String color, double precioUnitario, double precioVenta, int cantidad) {
        this.id = id;
        this.nombreFlor = nombreFlor;
        this.cantidadVaras = cantidadVaras;
        this.precioUnitario = precioUnitario;
    }

    // Constructor vacío
    public Flor() {
        this.id = 0;
        this.nombreFlor = "";
        this.cantidadVaras = 0;
        this.precioUnitario = 0.0;
    }

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
        this.nombreFlor = nombreFlor;
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

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        if (precioUnitario >= 0) {
            this.precioUnitario = precioUnitario;
        } else {
            throw new IllegalArgumentException("El precio unitario no puede ser negativo.");
        }
    }

    // Métodos adicionales
    public void actualizarStock(int cantidad) {
        if (this.cantidadVaras + cantidad >= 0) {
            this.cantidadVaras += cantidad;
        } else {
            throw new IllegalArgumentException("El stock no puede ser negativo.");
        }
    }

    public double calcularPrecioTotal(int cantidad) {
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0.");
        }
        return cantidad * precioUnitario;
    }

    @Override
    public String toString() {
        return "Flor{" +
                "id=" + id +
                ", nombreFlor='" + nombreFlor + '\'' +
                ", cantidadVaras=" + cantidadVaras +
                ", precioUnitario=" + precioUnitario +
                '}';
    }

    public double getPrecioCosto() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void setPrecioVenta(double d) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void add(Flor flor) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}

