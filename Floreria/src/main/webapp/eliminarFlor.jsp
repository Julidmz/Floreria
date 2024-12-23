<%@ page import="java.sql.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    // Obtenemos el parámetro "id" de la solicitud
    String idFlor = request.getParameter("id");
    
    // Variables de conexión
    Connection conn = null;
    PreparedStatement ps = null;

    try {
        // Configuración de la conexión
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/floreria", "root", "");

        // SQL para eliminar una flor con el ID especificado
        String sql = "DELETE FROM flor WHERE id = ?";
        ps = conn.prepareStatement(sql);
        ps.setInt(1, Integer.parseInt(idFlor));

        // Ejecutamos el DELETE
        int rowsAffected = ps.executeUpdate();
        
        if (rowsAffected > 0) {
            System.out.println("Flor eliminada exitosamente.");
        } else {
            System.out.println("No se encontró flor con ID: " + idFlor);
        }
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        // Cerramos la conexión y el statement
        if (ps != null) try { ps.close(); } catch (SQLException ignore) {}
        if (conn != null) try { conn.close(); } catch (SQLException ignore) {}
    }

    // Redirigimos de nuevo a listarZapatos.jsp después de la eliminación
    response.sendRedirect("listarFlores.jsp");
%>
