package dbhelpers;

import static dbhelpers.Constants.dbName;
import static dbhelpers.Constants.pwd;
import static dbhelpers.Constants.uname;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import model.Order;
import model.Product;

public class AddtoCartQuery {

	public Connection connection;
	private ResultSet results;
	HttpSession session;

	public AddtoCartQuery() {
		String url = "jdbc:mysql://localhost:3306/" + dbName;

		// setting up driver

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			this.connection = DriverManager.getConnection(url, uname, pwd);

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// Query to add products to the table

	public void doAdd(Order order) {
		String query = "INSERT INTO `Order` where `productid`=?";
		String query2 = " UPDATE INTO  `Product` `quantity` =? where `productid`=?";

		try {

			PreparedStatement ps = this.connection.prepareStatement(query);
			PreparedStatement ps2 = this.connection.prepareStatement(query2);

			this.results = ps.executeQuery();
			this.results = ps2.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getHTMLTable() {
		String table = "";

		try {
			while (this.results.next()) {

				Product product = new Product(this.results.getInt("productid"));
				Order order = new Order(this.results.getInt("orderid"));

				table += "<tr>";
				table += "<td>";
				table += product.getName();
				table += "</td>";
				table += "<td><img src='";
				table += product.getImg();
				table += "' height='70' width='70'>";
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
				table += order.getQuantity();
				table += "</td>";
				table += "<td>";
				table += order.getTotal();
				table += "</td>";

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		table += "</table>";
		return table;
	}

}
