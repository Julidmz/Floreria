<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Editar Flor</title>  
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <div class="container my-4">
        <h1 class="text-center">Editar Flor</h1>

        <!-- Verificar si el objeto flor est� disponible -->
        <c:if test="${flor != null}">
            <!-- Formulario de edici�n de flores -->
            <form action="editarFlor" method="post">
                <!-- Campo oculto para el ID -->
                <input type="hidden" name="id" value="${flor.id}">
                
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
                
                
                <button type="submit" class="btn btn-primary">Guardar Cambios</button>
                <a href="listarFlores.jsp" class="btn btn-dark">Volver</a>
            </form>
        </c:if>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
</body>
</html>
