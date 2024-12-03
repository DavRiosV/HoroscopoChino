package com.edutecno.servlets;
import com.edutecno.procesaconexion.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.edutecno.dao.HoroscopoDAOImp;
import com.edutecno.modelo.Horoscopo;

/**
 * Servlet implementation class ConsultarHoroscopo
 */
@WebServlet("/ConsultarHoroscopo")
public class ConsultarHoroscopo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ConsultarHoroscopo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String fechaNacimiento = request.getParameter("fechaNacimiento");

	    if (fechaNacimiento == null || fechaNacimiento.isEmpty()) {
	        request.setAttribute("error", "Debes ingresar una fecha válida.");
	        request.getRequestDispatcher("consulta.jsp").forward(request, response);
	        return;
	    }

	    try {
	        // Formato de la fecha ingresada desde el formulario
	        DateTimeFormatter formatoEntrada = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	        LocalDate fechaIngresada = LocalDate.parse(fechaNacimiento, formatoEntrada);

	        // Establecer conexión usando la clase Conexion
	        try (Connection conn = Conexion.getConexion()) {
	            String sql = "SELECT animal, fecha_inicio, fecha_fin FROM horoscopo";
	            try (PreparedStatement stmt = conn.prepareStatement(sql);
	                 ResultSet rs = stmt.executeQuery()) {

	                DateTimeFormatter formatoBaseDatos = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	                String animalEncontrado = null;

	                // Iterar sobre los resultados de la consulta
	                while (rs.next()) {
	                    String animal = rs.getString("animal");
	                    LocalDate fechaInicio = LocalDate.parse(rs.getString("fecha_inicio"), formatoBaseDatos);
	                    LocalDate fechaFin = LocalDate.parse(rs.getString("fecha_fin"), formatoBaseDatos);

	                    // Verificar si la fecha ingresada cae dentro del rango
	                    if (!fechaIngresada.isBefore(fechaInicio) && !fechaIngresada.isAfter(fechaFin)) {
	                        animalEncontrado = animal;
	                        break;
	                    }
	                }

	                // Enviar el resultado a la JSP
	                if (animalEncontrado != null) {
	                    String rutaImagen = "assets/img/" + animalEncontrado.toLowerCase() + ".webp"; // Ruta a la imagen
	                    request.setAttribute("animal", animalEncontrado);
	                    request.setAttribute("imagenAnimal", rutaImagen);
	                } else {
	                    request.setAttribute("error", "No se encontró un signo asociado a la fecha ingresada.");
	                }

	                request.getRequestDispatcher("consulta.jsp").forward(request, response);
	            }
	        }
	    } catch (DateTimeParseException e) {
	        request.setAttribute("error", "Formato de fecha incorrecto. Por favor usa el formato AAAA-MM-DD.");
	        request.getRequestDispatcher("consulta.jsp").forward(request, response);
	    } catch (Exception e) {
	        request.setAttribute("error", "Error al consultar la base de datos: " + e.getMessage());
	        request.getRequestDispatcher("consulta.jsp").forward(request, response);
	    }
	}

	}


