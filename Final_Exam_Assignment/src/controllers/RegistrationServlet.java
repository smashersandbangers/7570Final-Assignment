package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dbhelpers.AddUserQuery;
import model.User;



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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// get the data
			
		
		User user;
        HttpSession session = request.getSession();
        user = (User) session.getAttribute("user");

		
		int userid = Integer.parseInt(request.getParameter("userid"));
				String username =request.getParameter("username");
				String password =request.getParameter("password");
				String name =request.getParameter("name");
				int age=Integer.parseInt(request.getParameter("age"));
			
					
				//create the user object 
				
				 user = new User(userid, username, password, name, age);
				
				user.setUsername(username);
				user.setPassword(password);
				user.setName(name);
				user.setAge(age);
				
				//set up and add query obj
				
				AddUserQuery auq = new AddUserQuery("Cricket_Store_Database","root","password");
				
				auq.doAdd(user);
				
				
				
				//pass execution to the loginservlet 
				
				String url = "/login";
						
						RequestDispatcher dispatcher = request.getRequestDispatcher(url);
				dispatcher.forward(request, response);
				
				
	}

}
