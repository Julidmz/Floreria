<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%! 
// Clase para representar una flor seleccionado
public class Flor {
    private String nombre;
    private int cantidad;
    private double precio;

    // Constructor con parámetros
    public Flor(String nombre, int cantidad, double precio) {
        this.nombre = nombre;
        this.cantidad = cantidad >= 0 ? cantidad : 0; // Validación para evitar cantidad negativa
        this.precio = precio >= 0 ? precio : 0.0; // Validación para evitar precio negativo
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Clientes y Flores</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <!-- Barra de navegación -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Gestión de Clientes y Flores</a>
        </div>
    </nav>

    <!-- Contenido principal -->
    <div class="container my-4">
        <h1 class="text-center">Gestión Pedidos</h1>

        <!-- Formulario para seleccionar cliente -->
        <form method="GET" action="" class="mb-4">
            <div class="mb-3">
                <label for="cliente" class="form-label">Selecciona un Cliente:</label>
                <select class="form-select" id="cliente" name="idCliente" required>
                    <option value="" disabled selected>Selecciona un cliente</option>
                    <%
                        Connection conn = null;
                        PreparedStatement ps = null;
                        ResultSet rs = null;

                        try {
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/zapateria", "root", "");

                            String sql = "SELECT id, nombre, apellido FROM clientes ORDER BY apellido ASC";
                            ps = conn.prepareStatement(sql);
                            rs = ps.executeQuery();

                            while (rs.next()) {
                                int id = rs.getInt("id");
                                String nombre = rs.getString("nombre");
                                String apellido = rs.getString("apellido");
                    %>
                                <option value="<%= id %>" <%= (request.getParameter("idCliente") != null &&
                                Integer.toString(id).equals(request.getParameter("idCliente"))) ? "selected" : "" %>>
                                    <%= apellido + ", " + nombre %>
                                </option>
                    <%
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            if (rs != null) rs.close();
                            if (ps != null) ps.close();
                            if (conn != null) conn.close();
                        }
                    %>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Seleccionar Cliente</button>
        </form>

        <!-- Mostrar información del cliente seleccionado -->
        <%
            String idCliente = request.getParameter("idCliente");
            if (idCliente != null) {
                session.setAttribute("idCliente", idCliente);
                Connection conn2 = null;
                PreparedStatement ps2 = null;
                ResultSet rs2 = null;

                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    conn2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/zapateria", "root", "");

                    String sql2 = "SELECT * FROM clientes WHERE id = ?";
                    ps2 = conn2.prepareStatement(sql2);
                    ps2.setInt(1, Integer.parseInt(idCliente));
                    rs2 = ps2.executeQuery();

                    if (rs2.next()) {
        %>
                        <div class="card mb-4">
                            <div class="card-header bg-dark text-white">Información del Cliente</div>
                            <div class="card-body">
                                <p><strong>ID:</strong> <%= rs2.getInt("id") %></p>
                                <p><strong>Nombre:</strong> <%= rs2.getString("nombre") %></p>
                                <p><strong>Apellido:</strong> <%= rs2.getString("apellido") %></p>
                                <p><strong>Dirección:</strong> <%= rs2.getString("direccion") %></p>
                                <p><strong>Teléfono:</strong> <%= rs2.getString("telefono") %></p>
                                <p><strong>DNI:</strong> <%= rs2.getString("dni") %></p>
                                <p><strong>Correo:</strong> <%= rs2.getString("correo") %></p>
                            </div>
                        </div>
        <%
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (rs2 != null) rs2.close();
                    if (ps2 != null) ps2.close();
                    if (conn2 != null) conn2.close();
                }
            }
        %>

        <!-- Formulario para seleccionar zapatos -->
        <form method="POST" action="">
            <div class="mb-3">
                <label for="flor" class="form-label">Selecciona una Flor:</label>
                <select class="form-select" id="flor" name="idFlor">
                    <option value="" disabled selected>Selecciona una flor</option>
                    <%
                        Connection conn3 = null;
                        PreparedStatement ps3 = null;
                        ResultSet rs3 = null;

                        try {
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            conn3 = DriverManager.getConnection("jdbc:mysql://localhost:3306/zapateria", "root", "");

                            String sql3 = "SELECT id, modelo, talle, precio_venta FROM zapatos ORDER BY modelo ASC";
                            ps3 = conn3.prepareStatement(sql3);
                            rs3 = ps3.executeQuery();

                            while (rs3.next()) {
                                int id = rs3.getInt("id");
                                String modelo = rs3.getString("modelo");
                                int talle = rs3.getInt("talle");
                                double precioVenta = rs3.getDouble("precio_venta");
                    %>
                                <option value="<%= id %>"><%= modelo + " (Talle: " + talle + ", Precio Venta: $" + precioVenta  + ")" %></option>
                    <%
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            if (rs3 != null) rs3.close();
                            if (ps3 != null) ps3.close();
                            if (conn3 != null) conn3.close();
                        }
                    %>
                </select>
            </div>
            <button type="submit" class="btn btn-secondary">Agregar Zapato</button>
        </form>

        <!-- Lista de zapatos seleccionados -->
        <%
            if (request.getMethod().equalsIgnoreCase("POST")) {
                String idFlor = request.getParameter("idFlor");
                if (idFlor != null) {
                    ArrayList<Zapato> zapatosSeleccionados = (ArrayList<Flor>) session.getAttribute("floresSeleccionadas");
                    if (floresSeleccionadas == null) {
                        floresSeleccionadas = new ArrayList<>();
                    }

                    Connection connFlor = null;
                    PreparedStatement psFlor = null;
                    ResultSet rsFlor = null;

                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        connZapato = DriverManager.getConnection("jdbc:mysql://localhost:3306/zapateria", "root", "");

                        String sqlFlor = "SELECT modelo, talle, precio_venta FROM zapatos WHERE id = ?";
                        psFlor = connFlor.prepareStatement(sqlFlor);
                        psFlor.setInt(1, Integer.parseInt(idFlor));
                        rsFlor = psFlor.executeQuery();

                        if (rsFlor.next()) {
                            String modelo = rsFlor.getString("modelo");
                            int talle = rsFlor.getInt("talle");
                            double precio = rsFlor.getDouble("precio_venta");
                            floresSeleccionadas.add(new Flor(modelo, talle, precio));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (rsFlor != null) rsFlor.close();
                        if (psFlor != null) psFlor.close();
                        if (connFlor != null) connFlor.close();
                    }

                    session.setAttribute("floresSeleccionadas", floresSeleccionadas);
                }
            }

            double totalPrecio = 0.0;
            ArrayList<Flor> floresSeleccionadas = (ArrayList<Flor>) session.getAttribute("floresSeleccionadas");
            if (floresSeleccionadas != null) {
                for (Flor flor : floresSeleccionadas) {
                    totalPrecio += flor.precio;
                }
            }
        %>

        <!-- Mostrar las flores seleccionadas -->
        <div class="card">
            <div class="card-header bg-secondary text-white">Flores Seleccionadas</div>
            <div class="card-body">
                <ul id="florList" class="list-group list-group-flush">
                    <%
                        if (floresSeleccionadas != null && !floresSeleccionadas.isEmpty()) {
                            for (Flor flor : floresSeleccionadas) {
                    %>
                                <li class="list-group-item">
                                    <%= flor.modelo + " (Talle: " + flor.talle + ", Precio: $" + flor.precio + ")" %>
                                </li>
                    <%
                            }
                        } else {
                    %>
                            <li class="list-group-item">No hay flores seleccionadas</li>
                    <%
                        }
                    %>
                </ul>
                <p class="mt-3"><strong>Total:</strong> $<%= totalPrecio %></p>
            </div>
        </div>
    </div>
</body>
</html>
