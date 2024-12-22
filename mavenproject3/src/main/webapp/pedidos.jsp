<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%! 
// Clase para representar un zapato seleccionado
class Zapato {
    String modelo;
    int talle;
    double precio;

    public Zapato(String modelo, int talle, double precio) {
        this.modelo = modelo;
        this.talle = talle;
        this.precio = precio;
    }
}
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Clientes y Zapatos</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <!-- Barra de navegación -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Gestión de Clientes y Zapatos</a>
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
                <label for="zapato" class="form-label">Selecciona un Zapato:</label>
                <select class="form-select" id="zapato" name="idZapato">
                    <option value="" disabled selected>Selecciona un zapato</option>
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
                String idZapato = request.getParameter("idZapato");
                if (idZapato != null) {
                    ArrayList<Zapato> zapatosSeleccionados = (ArrayList<Zapato>) session.getAttribute("zapatosSeleccionados");
                    if (zapatosSeleccionados == null) {
                        zapatosSeleccionados = new ArrayList<>();
                    }

                    Connection connZapato = null;
                    PreparedStatement psZapato = null;
                    ResultSet rsZapato = null;

                    try {
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        connZapato = DriverManager.getConnection("jdbc:mysql://localhost:3306/zapateria", "root", "");

                        String sqlZapato = "SELECT modelo, talle, precio_venta FROM zapatos WHERE id = ?";
                        psZapato = connZapato.prepareStatement(sqlZapato);
                        psZapato.setInt(1, Integer.parseInt(idZapato));
                        rsZapato = psZapato.executeQuery();

                        if (rsZapato.next()) {
                            String modelo = rsZapato.getString("modelo");
                            int talle = rsZapato.getInt("talle");
                            double precio = rsZapato.getDouble("precio_venta");
                            zapatosSeleccionados.add(new Zapato(modelo, talle, precio));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        if (rsZapato != null) rsZapato.close();
                        if (psZapato != null) psZapato.close();
                        if (connZapato != null) connZapato.close();
                    }

                    session.setAttribute("zapatosSeleccionados", zapatosSeleccionados);
                }
            }

            double totalPrecio = 0.0;
            ArrayList<Zapato> zapatosSeleccionados = (ArrayList<Zapato>) session.getAttribute("zapatosSeleccionados");
            if (zapatosSeleccionados != null) {
                for (Zapato zapato : zapatosSeleccionados) {
                    totalPrecio += zapato.precio;
                }
            }
        %>

        <!-- Mostrar los zapatos seleccionados -->
        <div class="card">
            <div class="card-header bg-secondary text-white">Zapatos Seleccionados</div>
            <div class="card-body">
                <ul id="zapatosList" class="list-group list-group-flush">
                    <%
                        if (zapatosSeleccionados != null && !zapatosSeleccionados.isEmpty()) {
                            for (Zapato zapato : zapatosSeleccionados) {
                    %>
                                <li class="list-group-item">
                                    <%= zapato.modelo + " (Talle: " + zapato.talle + ", Precio: $" + zapato.precio + ")" %>
                                </li>
                    <%
                            }
                        } else {
                    %>
                            <li class="list-group-item">No hay zapatos seleccionados</li>
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
