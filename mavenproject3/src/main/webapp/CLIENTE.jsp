<%@ page import="java.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gestión de Clientes</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

    <!-- Barra de navegación -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Gestión de Clientes</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <a class="nav-link active" href="#">Listado de Clientes</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Agregar Cliente</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Eliminar Cliente</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Editar Cliente</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <!-- Contenido principal -->
    <div class="container my-4">
        <h1 class="text-center">Bienvenido al Sistema de Gestión de Clientes</h1>
        <p class="text-center">Aquí puedes gestionar el listado, agregar, eliminar y editar clientes.</p>
        
        <!-- Tabla de clientes -->
        <div class="table-responsive">
            <table class="table table-striped table-bordered">
                <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Apellido</th>
                        <th>Dirección</th>
                        <th>Teléfono</th>
                        <th>DNI</th>
                        <th>Correo</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        // Conectar a la base de datos y obtener la lista de clientes
                        Connection conn = null;
                        PreparedStatement ps = null;
                        ResultSet rs = null;

                        try {
                            // Configuración de la conexión
                            Class.forName("com.mysql.cj.jdbc.Driver");
                            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/zapateria", "root", "");

                            // Consulta para obtener todos los clientes
                            String sql = "SELECT * FROM clientes";
                            ps = conn.prepareStatement(sql);
                            rs = ps.executeQuery();

                            // Iterar sobre el resultado de la consulta y mostrar en la tabla
                            while (rs.next()) {
                                int id = rs.getInt("id");
                                String nombre = rs.getString("nombre");
                                String apellido = rs.getString("apellido");
                                String direccion = rs.getString("direccion");
                                String telefono = rs.getString("telefono");
                                String dni = rs.getString("dni");
                                String correo = rs.getString("correo");
                    %>
                                <tr>
                                    <td><%= id %></td>
                                    <td><%= nombre %></td>
                                    <td><%= apellido %></td>
                                    <td><%= direccion %></td>
                                    <td><%= telefono %></td>
                                    <td><%= dni %></td>
                                    <td><%= correo %></td>
                                    <td>
                                        <a href="editarCliente.jsp?id=<%= id %>" class="btn btn-primary btn-sm">Editar</a>
                                        <a href="eliminarCliente.jsp?id=<%= id %>" class="btn btn-danger btn-sm">Eliminar</a>
                                    </td>
                                </tr>
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
                </tbody>
            </table>
        </div>

        <!-- Botón para agregar un nuevo cliente -->
        <div class="d-flex justify-content-end">
            <a href="insertarCliente.jsp" class="btn btn-success mt-3">Agregar Nuevo Cliente</a>
        </div>
    </div>

    <!-- Bootstrap JS y dependencias -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
</body>
</html>
