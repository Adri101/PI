package es.unex.pi.controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import es.unex.pi.dao.JDBCUserDAOImpl;
import es.unex.pi.dao.UserDAO;
import es.unex.pi.model.User;

/**
 * Servlet implementation class IniciarSesionServlet
 */
@WebServlet("/IniciarSesionServlet.do")
public class IniciarSesionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IniciarSesionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute("CheckType", "Inicia Sesión");
		request.setAttribute("Button", "Inicia sesión aqui");
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/Registro.jsp");
		view.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		Connection conn = (Connection) getServletContext().getAttribute("dbConn");
		UserDAO userDAO = new JDBCUserDAOImpl();
		userDAO.setConnection(conn);

		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		User user = new User();
		user = userDAO.get(username);
		if (user!=null && user.getUsername().equals(username) && user.getEmail().equals(email)
				&& user.getPassword().equals(password)) {
			HttpSession sesion = request.getSession();
			sesion.setAttribute("user", user);
			response.sendRedirect("pages/index.html");
		}else {
			request.setAttribute("info", "Datos erroneos, vuelva a introducirlo");
			request.setAttribute("CheckType", "Inicia sesión");
			request.setAttribute("Button", "Inicia sesión aquí");
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/Registro.jsp");
			view.forward(request, response);
		}
	}

}
