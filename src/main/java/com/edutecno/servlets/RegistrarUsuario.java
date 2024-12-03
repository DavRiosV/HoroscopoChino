package com.edutecno.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.edutecno.modelo.Usuario;
import com.edutecno.procesaconexion.Conexion;

@WebServlet("/RegistrarUsuario")
public class RegistrarUsuario extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RegistrarUsuario() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String fechaNacimiento = request.getParameter("fechaNacimiento");
        String password = request.getParameter("password");

        if (nombre.isEmpty() || username.isEmpty() || email.isEmpty() || fechaNacimiento.isEmpty() || password.isEmpty()) {
            request.setAttribute("error", "Todos los campos son obligatorios.");
            request.getRequestDispatcher("registro.jsp").forward(request, response);
            return;
        }

        String animal = calcularAnimalZodiacal(fechaNacimiento);

        if (animal == null) {
            request.setAttribute("error", "No se pudo determinar el signo zodiacal.");
            request.getRequestDispatcher("registro.jsp").forward(request, response);
            return;
        }

        try (Connection conn = Conexion.getConexion()) {
            String sql = "INSERT INTO usuarios (nombre, username, email, fecha_nacimiento, password, animal) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, nombre);
                stmt.setString(2, username);
                stmt.setString(3, email);
                stmt.setDate(4, java.sql.Date.valueOf(fechaNacimiento));
                stmt.setString(5, password);
                stmt.setString(6, animal);
                stmt.executeUpdate();
            }

            // Crear un objeto Usuario y almacenarlo en la sesión
            Usuario usuario = new Usuario();
            usuario.setNombre(nombre);
            usuario.setUsername(username);
            usuario.setEmail(email);
            usuario.setFechaNacimiento(java.sql.Date.valueOf(fechaNacimiento));
            usuario.setAnimal(animal);

            HttpSession sesion = request.getSession();
            sesion.setAttribute("usuario", usuario);

            response.sendRedirect("index.jsp?mensaje=" + URLEncoder.encode("Usuario registrado con éxito", "UTF-8"));

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al registrar el usuario: " + e.getMessage());
            request.getRequestDispatcher("registro.jsp").forward(request, response);
        }
    }

    private String calcularAnimalZodiacal(String fechaNacimiento) {
        try {
            DateTimeFormatter formatoEntrada = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate fechaIngresada = LocalDate.parse(fechaNacimiento, formatoEntrada);

            try (Connection conn = Conexion.getConexion()) {
                String sql = "SELECT animal, fecha_inicio, fecha_fin FROM horoscopo";
                try (PreparedStatement stmt = conn.prepareStatement(sql);
                     ResultSet rs = stmt.executeQuery()) {

                    DateTimeFormatter formatoBaseDatos = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                    while (rs.next()) {
                        String animal = rs.getString("animal");
                        LocalDate fechaInicio = LocalDate.parse(rs.getString("fecha_inicio"), formatoBaseDatos);
                        LocalDate fechaFin = LocalDate.parse(rs.getString("fecha_fin"), formatoBaseDatos);

                        if (!fechaIngresada.isBefore(fechaInicio) && !fechaIngresada.isAfter(fechaFin)) {
                            return animal;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
