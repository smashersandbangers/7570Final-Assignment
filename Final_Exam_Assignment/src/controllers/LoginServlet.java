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
 * Servlet implementation class LoginServlet
 */
@WebServlet(description = "Controller that will handle the login  authenticate and pass on to browse", urlPatterns = {
		"/Login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	HttpSession session;
	String url = "";
	int loginattempts;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// process get response and see if the user has logged out

		session = request.getSession();

		// check to make suser has clicked link

		if (request.getParameter("logout") != null) {

			// logout and redirect to index
			logout();

			url = "index.jsp";
		}

		// forward our request along
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// process the post request/response

		// get current session
		session = request.getSession();

		// get the number of logins
		if (session.getAttribute("loginAttempts") == null) {
			loginattempts = 0;
		}

		// exceeded logins
		if (loginattempts > 2) {
			String message = "Error: Number of Login Attempts Exceeded";
			request.setAttribute("message", message);
			url = "index.jsp";
		} else {
			// proceed pull the fields from the form

			String username = request.getParameter("username");
			String password = request.getParameter("password");

			// encrypt the password to check against what's stored in DB
			PasswordService pws = new PasswordService();
			String encryptedPass = pws.encrypt(password);

			// create a user helper class to make database calls, and call
			// authenticate user method

			User user = new User();
			user.setUsername(username);
			user.setPassword(encryptedPass);
			user = user.authenticate();

			// we've found a user that matches the credentials
			if (user.getName() != null) {
				// invalidate current session, then get new session for our user
				// (combats: session hijacking)
				session.invalidate();
				session = request.getSession(true);
				session.setAttribute("userId", user.getUserid());
				session.setAttribute("userName", user.getUsername());
				session.setAttribute("name", user.getName());
				url = "/UserAccount.jsp";
			}
			// user doesn't exist, redirect to previous page and show error
			else {
				String message = "Error: Unrecognized Username or Password";
				request.setAttribute("message", message);

				// track login attempts (combats: brute force attacks)
				session.setAttribute("loginAttempts", loginattempts++);
				url = "/index.jsp";
			}
		}
		// forward our request along
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

	private void logout() {
		session.invalidate();

	}

}
