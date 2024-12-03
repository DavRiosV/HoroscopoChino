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
import java.sql.ResultSet;

import com.edutecno.modelo.Usuario;
import com.edutecno.procesaconexion.Conexion;

@WebServlet("/usuario")
public class UsuarioServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");

        if ("login".equals(accion)) {
            String email = request.getParameter("usuario");
            String password = request.getParameter("clave");

            if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
                request.setAttribute("error", "Todos los campos son obligatorios.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
                return;
            }

            try (Connection conn = Conexion.getConexion()) {
                // Consulta para validar las credenciales
                String sql = "SELECT id, nombre, username, email, fecha_nacimiento, password, animal FROM usuarios WHERE email = ? AND password = ?";
                try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, email);
                    stmt.setString(2, password);

                    try (ResultSet rs = stmt.executeQuery()) {
                        if (rs.next()) {
                            // Crear el objeto Usuario con todos los datos
                            Usuario user = new Usuario(
                                rs.getInt("id"),
                                rs.getString("nombre"),
                                rs.getString("username"),
                                rs.getString("email"),
                                rs.getDate("fecha_nacimiento"),
                                rs.getString("password"),
                                rs.getString("animal")
                            );

                            // Crear la sesión y guardar el objeto Usuario
                            HttpSession sesion = request.getSession();
                            sesion.setAttribute("usuario", user);

                            // Redirigir al index.jsp
                            response.sendRedirect("index.jsp");
                        } else {
                            // Usuario o contraseña incorrectos
                            request.setAttribute("error", "Usuario o contraseña incorrectos.");
                            request.getRequestDispatcher("login.jsp").forward(request, response);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("error", "Error al procesar el login: " + e.getMessage());
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } else {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Acción no reconocida.");
        }
    }
}
