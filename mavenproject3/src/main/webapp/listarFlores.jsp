<%@page import="java.util.List"%>
<%@ page import="dao.FlorDAO" %>
<%@ page import="model.Flor" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inventario de Flores</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

    <!-- Barra de navegación -->
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">
            <a class="navbar-brand" href="#">Inventario de Flores</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav ms-auto">
                    <li class="nav-item">
                        <a class="nav-link active" href="#">Listado de Flores</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="insertarZapato.jsp">Agregar Flor</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>

    <!-- Contenido principal -->
    <div class="container my-4">
        <h1 class="text-center">Listado de Flores</h1>

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

        <!-- Tabla de flores -->
        <div class="table-responsive">
            <table class="table table-striped table-bordered">
                <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>Modelo</th>
                        <th>Marca</th>
                        <th>Talle</th>
                        <th>Color</th>
                        <th>Precio Costo</th>
                        <th>Precio Venta</th>
                        <th>Cantidad</th>
                        <th>Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        // Instanciar el DAO y obtener la lista de zapatos
                        FlorDAO florDAO = new FlorDAO();
                        List<Flor> flor = florDAO.obtenerFlor();

                        for (Zapato zapato : zapatos) {
                    %>
                    <tr>
                        <td><%= flor.getId() %></td>
                        <td><%= flor.getModelo() %></td>
                        <td><%= flor.getMarca() %></td>
                        <td><%= flor.getTalle() %></td>
                        <td><%= flor.getColor() %></td>
                        <td><%= flor.getPrecioCosto() %></td>
                        <td><%= flor.getPrecioVenta() %></td>
                        <td><%= flor.getCantidad() %></td>
                        <td>
                            <a href="editarZapato?id=<%= flor.getId() %>" class="btn btn-primary btn-sm">Editar</a>
                            <a href="eliminarZapato?id=<%= flor.getId() %>" class="btn btn-danger btn-sm" onclick="return confirm('¿Estás seguro de que deseas eliminar esta flor?');">Eliminar</a>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
        </div>

        <!-- Botón para agregar una nueva flor -->
        <div class="d-flex justify-content-end">
            <a href="insertarFlor.jsp" class="btn btn-success mt-3">Agregar Nueva Flor</a>
        </div>
    </div>

    <!-- Bootstrap JS y dependencias -->
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
</body>
</html>
