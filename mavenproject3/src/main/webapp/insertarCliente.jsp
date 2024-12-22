<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Insertar Cliente</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-4">
    <h2>Agregar Nuevo Cliente</h2>
    
    <!-- Formulario para ingresar un cliente -->
    <form method="post" action="insertarCliente">
        <div class="mb-3">
            <label for="nombre" class="form-label">Nombre:</label>
            <input type="text" class="form-control" id="nombre" name="nombre" required>
        </div>
        <div class="mb-3">
            <label for="apellido" class="form-label">Apellido:</label>
            <input type="text" class="form-control" id="apellido" name="apellido" required>
        </div>
        <div class="mb-3">
            <label for="direccion" class="form-label">Dirección:</label>
            <input type="text" class="form-control" id="direccion" name="direccion" required>
        </div>
        <div class="mb-3">
            <label for="telefono" class="form-label">Teléfono:</label>
            <input type="text" class="form-control" id="telefono" name="telefono">
        </div>
        <div class="mb-3">
            <label for="dni" class="form-label">DNI:</label>
            <input type="text" class="form-control" id="dni" name="dni" required>
        </div>
        <div class="mb-3">
            <label for="correo" class="form-label">Correo Electrónico:</label>
            <input type="email" class="form-control" id="correo" name="correo" required>
        </div>
        <button type="submit" class="btn btn-success">Agregar Cliente</button>
        <a href="listarClientes.jsp" class="btn btn-dark">Volver</a>
    </form>

    <!-- Mostrar mensaje de éxito o error -->
    <%
        String message = (String) request.getAttribute("message");
        if (message != null) {
            out.println("<div class='alert alert-info mt-4'>" + message + "</div>");
        }
    %>

</div>

<!-- Bootstrap JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
