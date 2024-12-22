<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Insertar Flor</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-4">
    <h2>Agregar Nueva Flor</h2>
    
    <!-- Formulario para ingresar un zapato -->
    <form method="post" action="insertarFlor">
        <div class="mb-3">
            <label for="modelo" class="form-label">Modelo:</label>
            <input type="text" class="form-control" id="modelo" name="modelo" required>
        </div>
        <div class="mb-3">
            <label for="marca" class="form-label">Marca:</label>
            <input type="text" class="form-control" id="marca" name="marca" required>
        </div>
        <div class="mb-3">
            <label for="talle" class="form-label">Talle:</label>
            <input type="number" class="form-control" id="talle" name="talle" min="1" required>
        </div>
        <div class="mb-3">
            <label for="color" class="form-label">Color:</label>
            <input type="text" class="form-control" id="color" name="color" required>
        </div>
        <div class="mb-3">
            <label for="precio_costo" class="form-label">Costo:</label>
            <input type="number" step="0.01" class="form-control" id="precio_costo" name="precio_costo" required>
        </div>
        <div class="mb-3">
            <label for="cantidad" class="form-label">Cantidad:</label>
            <input type="number" class="form-control" id="cantidad" name="cantidad" min="1" required>
        </div>
        <button type="submit" class="btn btn-success">Agregar Flor</button>
        <a href="listarFlores.jsp" class="btn btn-dark">Volver</a>
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
