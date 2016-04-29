package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbhelpers.UserQuery;
import model.User;
import utilities.PasswordService;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet(description = "Controller that will handle the login  authenticate and pass on to browse", urlPatterns = { "/LoginServlet" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	HttpSession session;
	String url="";
	int loginattempts;

	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//User has clicked the logout link
				session = request.getSession();

				//check to make sure we've clicked link
				if(request.getParameter("logout") !=null){

					//logout and redirect to index
					logout();
					url="index.jsp";
				}

				//forward our request along
				RequestDispatcher dispatcher = request.getRequestDispatcher(url);
				dispatcher.forward(request, response);
			}
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//get  current session
				session = request.getSession();

				//get the number of logins
				if(session.getAttribute("loginAttempts") == null){
					loginattempts = 0;
				}
				
				//exceeded logins
				if(loginattempts > 2){
					String errorMessage = "Error: Number of Login Attempts Exceeded";
					request.setAttribute("errorMessage", errorMessage);
					url = "index.jsp";
				}else
					{	//proceed pull the fields from the form
					String username = request.getParameter("username");
					String password = request.getParameter("password");

					//encrypt the password to check against what's stored in DB
					PasswordService pws = new PasswordService();
					String encryptedPass = pws.encrypt(password);
					
					//create a user helper class to make database calls, and call authenticate user method
					UserQuery uq = new UserQuery();
					User user = uq.authenticateUser( loginattempts, loginattempts, username, encryptedPass, encryptedPass);

					//we've found a user that matches the credentials
					if(user != null){
						//invalidate current session, then get new session for our user (combats: session hijacking)
						session.invalidate();
						session=request.getSession(true);
						session.setAttribute("user", user);
						url="browse.jsp";
					}
					// user doesn't exist, redirect to previous page and show error
					else{
						String errorMessage = "Error: Unrecognized Username or Password<br>Login attempts remaining: "+(3-(loginattempts));
						request.setAttribute("errorMessage", errorMessage);

						//track login attempts (combats: brute force attacks)
						session.setAttribute("loginAttempts", loginattempts++);
						url = "index.jsp";
					}
				}
				//forward our request along
				RequestDispatcher dispatcher = request.getRequestDispatcher(url);
				dispatcher.forward(request, response);
			}



	private void logout() {
		session.invalidate();
		
	}
	
	
}
