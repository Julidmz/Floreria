package model;

public class Cliente {
    private int id; // ID único del cliente, autoincremental
    private String nombre; // Nombre del cliente
    private String apellido; // Apellido del cliente
    private String direccion; // Dirección del cliente
    private String telefono; // Teléfono del cliente (puede ser null)
    private String dni; // DNI del cliente, debe ser único
    private String correo; // Correo electrónico del cliente, debe ser único

    // Constructor vacío (necesario para frameworks y serialización)
    public Cliente() {
    }

    // Constructor completo
    public Cliente(int id, String nombre, String apellido, String direccion, String telefono, String dni, String correo) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.direccion = direccion;
        this.telefono = telefono;
        this.dni = dni;
        this.correo = correo;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    // Método toString (opcional, para debug o representación textual)
    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", dni='" + dni + '\'' +
                ", correo='" + correo + '\'' +
                '}';
    }
}
