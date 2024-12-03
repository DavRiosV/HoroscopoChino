package com.edutecno.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.edutecno.dao.UsuarioDAOImp;
import com.edutecno.modelo.Usuario;

/**
 * Servlet implementation class ModificarUsuarioServlet
 */
@WebServlet("/ModificarUsuarioServlet")
public class ModificarUsuarioServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModificarUsuarioServlet() {
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
	        // Verificar si hay una sesión activa
	        HttpSession sesion = request.getSession(false);
	        if (sesion == null || sesion.getAttribute("usuario") == null) {
	            response.sendRedirect("login.jsp");
	            return;
	        }

	        // Obtener los datos actuales del usuario desde la sesión
	        Usuario usuario = (Usuario) sesion.getAttribute("usuario");

	        // Obtener los nuevos datos enviados desde el formulario
	        String nuevoNombre = request.getParameter("nombre");
	        String nuevoEmail = request.getParameter("email");
	        String nuevaPassword = request.getParameter("password");

	        // Actualizar los datos del usuario
	        usuario.setNombre(nuevoNombre);
	        usuario.setEmail(nuevoEmail);
	        usuario.setPassword(nuevaPassword);

	        // Llamar al DAO para actualizar la base de datos
	        UsuarioDAOImp usuarioDAO = new UsuarioDAOImp();
	        try {
	            boolean actualizado = usuarioDAO.actualizarUsuario(usuario);

	            if (actualizado) {
	                // Actualizar los datos en la sesión
	                sesion.setAttribute("usuario", usuario);

	                // Redirigir a la página con un mensaje de éxito
	                response.sendRedirect("modificarUsuario.jsp?mensaje=exito");
	            } else {
	                // Redirigir con un mensaje de error
	                request.setAttribute("error", "Error al actualizar los datos.");
	                request.getRequestDispatcher("modificarUsuario.jsp").forward(request, response);
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	            request.setAttribute("error", "Error al actualizar los datos: " + e.getMessage());
	            request.getRequestDispatcher("modificarUsuario.jsp").forward(request, response);
	        }
	    }
	}

