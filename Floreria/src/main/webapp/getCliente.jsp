<%@ page import="java.sql.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Obtén el clienteId de la solicitud
    String clienteId = request.getParameter("id");

    // Verificar si el clienteId es válido
    if (clienteId != null && !clienteId.trim().isEmpty()) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/floreria", "root", "");
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM clientes WHERE id = ?")) {

            ps.setInt(1, Integer.parseInt(clienteId)); // Establecer el clienteId en la consulta
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                out.println("<div class='card-body'>");
                out.println("<p><strong>ID:</strong> " + rs.getInt("id") + "</p>");
                out.println("<p><strong>Nombre:</strong> " + rs.getString("nombre") + "</p>");
                out.println("<p><strong>Apellido:</strong> " + rs.getString("apellido") + "</p>");
                out.println("<p><strong>Dirección:</strong> " + rs.getString("direccion") + "</p>");
                out.println("<p><strong>Teléfono:</strong> " + rs.getString("telefono") + "</p>");
                out.println("<p><strong>DNI:</strong> " + rs.getString("dni") + "</p>");
                out.println("<p><strong>Correo:</strong> " + rs.getString("correo") + "</p>");
                out.println("</div>");
            } else {
                // Si no se encuentra el cliente, mostrar un mensaje
                out.println("<p>Cliente no encontrado.</p>");
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Mostrar detalles del error si ocurre una excepción SQL
            out.println("<p>Error al conectar con la base de datos: " + e.getMessage() + "</p>");
        }
    } else {
        // Si el clienteId es nulo o vacío, mostrar un mensaje de error
        out.println("<p>Debe seleccionar un cliente.</p>");
    }
%>
