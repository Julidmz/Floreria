package com.example.Floreria.model;

public class Cliente {
    private int id;
    private String nombre;
    private String apellido;
    private String dni;
    private String direccion;
    private String telefono;
    private String email;

    // Constructor con 7 parámetros (incluyendo dni)
    public Cliente(int id, String nombre, String apellido, String dni, String direccion, String telefono, String email) {
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo ni vacío.");
        }
        if (apellido == null || apellido.isEmpty()) {
            throw new IllegalArgumentException("El apellido no puede ser nulo ni vacío.");
        }
        if (dni == null || dni.isEmpty() || !dni.matches("\\d{8}")) {
            throw new IllegalArgumentException("El DNI debe ser un número de 8 dígitos.");
        }
        if (direccion == null || direccion.isEmpty()) {
            throw new IllegalArgumentException("La dirección no puede ser nula ni vacía.");
        }
        if (telefono == null || telefono.isEmpty() || !telefono.matches("\\d+")) {
            throw new IllegalArgumentException("El teléfono debe ser un número válido.");
        }
        if (email == null || email.isEmpty() || !email.contains("@")) {
            throw new IllegalArgumentException("El email debe ser válido.");
        }

        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
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
        if (nombre == null || nombre.isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo ni vacío.");
        }
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        if (apellido == null || apellido.isEmpty()) {
            throw new IllegalArgumentException("El apellido no puede ser nulo ni vacío.");
        }
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        if (dni == null || dni.isEmpty() || !dni.matches("\\d{8}")) {
            throw new IllegalArgumentException("El DNI debe ser un número de 8 dígitos.");
        }
        this.dni = dni;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        if (direccion == null || direccion.isEmpty()) {
            throw new IllegalArgumentException("La dirección no puede ser nula ni vacía.");
        }
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if (telefono == null || telefono.isEmpty() || !telefono.matches("\\d+")) {
            throw new IllegalArgumentException("El teléfono debe ser un número válido.");
        }
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null || email.isEmpty() || !email.contains("@")) {
            throw new IllegalArgumentException("El email debe ser válido.");
        }
        this.email = email;
    }

    // Métodos
    public void registrarCliente() {
        System.out.println("Cliente registrado: " + nombre + " " + apellido);
    }

    public void actualizarDatos(String direccion, String telefono, String email) {
        if (direccion != null && !direccion.isEmpty()) {
            this.direccion = direccion;
        }
        if (telefono != null && !telefono.isEmpty() && telefono.matches("\\d+")) {
            this.telefono = telefono;
        }
        if (email != null && !email.isEmpty() && email.contains("@")) {
            this.email = email;
        }
        System.out.println("Datos del cliente actualizados.");
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", dni='" + dni + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}

