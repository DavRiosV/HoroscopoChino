package com.edutecno.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.edutecno.procesaconexion.Conexion;
import com.edutecno.modelo.Usuario;

@WebServlet("/EliminarUsuarioServlet")
public class EliminarUsuarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sesion = request.getSession(false);
        if (sesion == null || sesion.getAttribute("usuario") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        Usuario usuario = (Usuario) sesion.getAttribute("usuario");

        try (Connection conn = Conexion.getConexion()) {
            String sql = "DELETE FROM usuarios WHERE id = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, usuario.getId());
                int filasAfectadas = stmt.executeUpdate();

                if (filasAfectadas > 0) {
                    response.sendRedirect("usuario.jsp?mensaje=Cuenta eliminada con exito");
                } else {
                    request.setAttribute("error", "Error al intentar eliminar la cuenta.");
                    request.getRequestDispatcher("usuario.jsp").forward(request, response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al eliminar el usuario: " + e.getMessage());
            request.getRequestDispatcher("usuario.jsp").forward(request, response);
        }
    }
}
