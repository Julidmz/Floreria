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
            <label for="id" class="form-label">ID:</label>
            <input type="number" class="form-control" id="id" name="id" min="1" required>
        </div>
        <div class="mb-3">
            <label for="nombreFlor" class="form-label">Nombre de la Flor:</label>
            <input type="text" class="form-control" id="nombreFlor" name="nombreFlor" required>
        </div>
        <div class="mb-3">
            <label for="cantidadVaras" class="form-label">Cantidad de Varas:</label>
            <input type="number" class="form-control" id="cantidadVaras" name="cantidadVaras" min="1" required>
        </div>
        <div class="mb-3">
            <label for="precioVenta" class="form-label">Precio de Venta:</label>
            <input type="number" step="0.01" class="form-control" id="precioVenta" name="precioVenta" required>
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
