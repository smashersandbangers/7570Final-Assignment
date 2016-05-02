package controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Cart;
import model.Product;

@WebServlet(description = "Controller that will handle the login  authenticate and pass on to browse", urlPatterns = {
		"/ViewCart" })
public class ViewCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	HttpSession session;
	String url = "";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
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

		Cart cart = new Cart((int) session.getAttribute("userId"));

		HashMap<Integer, Integer> productsInCart = cart.getProductsInCart();

		String table = "";

		Iterator<Integer> it = productsInCart.keySet().iterator();
		while (it.hasNext()) {
			table += "<tr>";
			int productId = it.next();
			Product product = new Product(productId);

			table += "<td>";
			table += product.getName();
			table += "</td>";

			table += "<td><img height=\"60\" width=\"60\" src=\"";
			table += product.getImg();
			table += "\"/></td>";

			table += "<td>";
			table += product.getDesc();
			table += "</td>";

			table += "<td>";
			table += product.getPrice();
			table += "</td>";

			table += "<td>";
			table += productsInCart.get(productId);
			table += "</td>";

			table += "<td>";
			table += "<a href=Delete?productId=" + productId + ">Delete</a>";
			// table += "<a href = Delete>Login</a>";
			table += "</td>";

			table += "</tr>";
		}

		// pass execution to browser.jsp
		request.setAttribute("table", table);
		request.setAttribute("totalPrice", cart.getTotalPrice());
		String url = "/ViewCart.jsp";

		// forward our request along
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

	private void logout() {
		session.invalidate();

	}

}
