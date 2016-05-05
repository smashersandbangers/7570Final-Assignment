package controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cart;
import model.Product;

/**
 * Servlet implementation class AddtoCartServlet
 */
@WebServlet(urlPatterns = { "/Add" })
public class AddtoCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddtoCartServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// create a browse query helper object

		Cart cart = new Cart((int) request.getSession().getAttribute("userId"));
		int productId = Integer.parseInt(request.getParameter("productId"));
		String url = "";
		String strQuantity = request.getParameter("quantity");

		if (strQuantity == null) {
			Product product = new Product(productId);
			String table = "";
			table += "<tr>";
			table += "<td>";
			table += product.getName();
			table += "</td>";
			table += "<td><img src='";
			table += product.getImg();
			table += "' height='60' width='60'>";
			table += "</td>";
			table += "<td>";
			table += product.getDesc();
			table += "</td>";
			table += "<td>";
			table += product.getPrice();
			table += "</td>";
			table += "<td>";
			table += product.getQuantity();
			table += "</td>";

			table += "<td>";
			table += "<form action=\"Add\" method=\"POST\"> <input type=\"text\" name=\"quantity\" required value=\"0\"> <input type=\"hidden\" name=\"productId\" value=\""
					+ product.getId() + "\"> <input type=\"submit\" value=\"Add\">";
			table += "</td>";
			table += "</tr>";
			request.setAttribute("table", table);
			url = "/AddCart.jsp";
		} else {
			int quantity = Integer.parseInt(request.getParameter("quantity"));
			cart.addProducts(productId, quantity);
			url = "/ViewCart";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);

	}

}
