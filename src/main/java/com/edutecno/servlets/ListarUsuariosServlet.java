package com.edutecno.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

import com.edutecno.dao.UsuarioDAOImp;
import com.edutecno.modelo.Usuario;

/**
 * Servlet implementation class ListarUsuariosServlet
 */
@WebServlet("/ListarUsuariosServlet")
public class ListarUsuariosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListarUsuariosServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UsuarioDAOImp usuarioDAO = new UsuarioDAOImp();

        try {
            // Obtener la lista de usuarios
            List<Usuario> listaUsuarios = usuarioDAO.obtenerUsuarios();

            // Pasar la lista a la JSP
            if (listaUsuarios != null && !listaUsuarios.isEmpty()) {
                request.setAttribute("usuarios", listaUsuarios);
            } else {
                request.setAttribute("error", "No hay usuarios registrados.");
            }

            // Redirigir a listarUsuarios.jsp
            request.getRequestDispatcher("listarUsuarios.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Error al obtener la lista de usuarios: " + e.getMessage());
            request.getRequestDispatcher("listarUsuarios.jsp").forward(request, response);
        }
    }
 

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
