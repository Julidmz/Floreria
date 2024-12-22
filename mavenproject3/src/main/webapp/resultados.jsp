<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Resultado de la Inserción</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<div class="container mt-4">
    <h2>Resultado de la operación</h2>

    <!-- Mostrar mensaje de éxito o error -->
    <%
        String message = (String) request.getAttribute("message");
        if (message != null) {
            out.println("<div class='alert alert-info mt-4'>" + message + "</div>");
        }
    %>

    <a href="insertarZapato.jsp" class="btn btn-dark">Volver al formulario</a>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
