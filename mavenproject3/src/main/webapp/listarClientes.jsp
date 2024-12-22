<%@page import="java.util.List"%>
<%@ page import="dao.ClienteDAO" %>
<%@ page import="model.Cliente" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Listado de Clientes</title>
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
                        <a class="nav-link" href="insertarCliente.jsp">Agregar Cliente</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <!-- Contenido principal -->
    <div class="container my-4">
        <h1 class="text-center">Listado de Clientes</h1>

        <!-- Mostrar mensajes -->
        <%
            String message = request.getParameter("message");
            String error = request.getParameter("error");

            if (message != null) {
        %>
            <div class="alert alert-success alert-dismissible fade show" role="alert">
                <%= message %>
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        <%
            } else if (error != null) {
        %>
            <div class="alert alert-danger alert-dismissible fade show" role="alert">
                <%= error %>
                <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
            </div>
        <%
            }
        %>

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
                        // Instanciar el DAO y obtener la lista de clientes
                        ClienteDAO clienteDAO = new ClienteDAO();
                        List<Cliente> clientes = clienteDAO.obtenerClientes();

                        for (Cliente cliente : clientes) {
                    %>
                    <tr>
                        <td><%= cliente.getId() %></td>
                        <td><%= cliente.getNombre() %></td>
                        <td><%= cliente.getApellido() %></td>
                        <td><%= cliente.getDireccion() %></td>
                        <td><%= cliente.getTelefono() %></td>
                        <td><%= cliente.getDni() %></td>
                        <td><%= cliente.getCorreo() %></td>
                        <td>
                            <a href="editarCliente?id=<%= cliente.getId() %>" class="btn btn-primary btn-sm">Editar</a>
                            <a href="eliminarCliente?id=<%= cliente.getId() %>" class="btn btn-danger btn-sm" onclick="return confirm('¿Estás seguro de que deseas eliminar este cliente?');">Eliminar</a>
                        </td>
                    </tr>
                    <%
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
