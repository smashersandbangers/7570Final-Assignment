package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import utilities.PasswordService;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet(description = "Servlet to add User to the Database", urlPatterns = { "/Register" })
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistrationServlet() {
		super();
		// TODO Auto-generated constructor stub

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		String name = request.getParameter("name");
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		// ecrypt password to check against db

		PasswordService pws = new PasswordService();
		password = pws.encrypt(password);

		// create the user object
		User user = new User();
		// get current session
		user.setUsername(username);
		user.setPassword(password);
		user.setName(name);
		boolean userSaved = user.save();

		// set up and add query obj

		// pass execution to the loginservlet

		String url;
		if (userSaved)
			url = "/login.jsp";
		else
			url = "/registration.jsp";

		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);

	}

}
