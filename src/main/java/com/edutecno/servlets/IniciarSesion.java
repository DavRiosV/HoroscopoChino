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
 * Servlet implementation class IniciarSesion
 */
@WebServlet("/IniciarSesion")
public class IniciarSesion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IniciarSesion() {
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
	        String username = request.getParameter("username");
	        String password = request.getParameter("password");

	        UsuarioDAOImp usuarioDAO = new UsuarioDAOImp();
	        Usuario usuario = usuarioDAO.validarUsuario(username, password);

	        if (usuario != null) {
	            // Crear sesión y almacenar el usuario
	            HttpSession session = request.getSession();
	            session.setAttribute("usuario", usuario.getNombre());
	            session.setAttribute("animal", usuario.getAnimal());

	            // Redirigir a index.jsp
	            response.sendRedirect("index.jsp");
	        } else {
	            // Redirigir de vuelta al login con mensaje de error
	            response.sendRedirect("login.jsp?error=true");
	        }
	    }

}
