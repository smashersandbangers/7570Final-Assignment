package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dbhelpers.BrowseQuery;

/**
 * Servlet implementation class BrowserServlet
 */
@WebServlet(
		description = "Controller to direct the user from the login and cart page", 
		urlPatterns = { 
				 "/BrowserServlet",
			
		})
public class BrowserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BrowserServlet() {
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
		// TODO Auto-generated method stub
		
		
		//create a browse query helper object
			BrowseQuery bq =new BrowseQuery("Cricket_Store_Databse","root","password"){
				};
				
				
				//get html table from read query object
				
				bq.doBrowse();
				String table = bq.getHTMLTable();
				
				// pass execution to read.jsp
				request.setAttribute("table", table);
				String url = "/browse.jsp";
				
				RequestDispatcher dispatcher = request.getRequestDispatcher(url);
				dispatcher.forward(request, response);
				
				
				
				
			}

		}

