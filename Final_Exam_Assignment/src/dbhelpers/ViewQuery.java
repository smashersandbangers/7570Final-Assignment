package dbhelpers;

import static dbhelpers.Constants.dbName;
import static dbhelpers.Constants.pwd;
import static dbhelpers.Constants.uname;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;

import model.Product;

public class ViewQuery {

	private Connection connection;
	private ResultSet results;

	public ViewQuery() {
		String url = "jdbc:mysql://localhost:3306/" + dbName;

		// set up driver

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			this.connection = (Connection) DriverManager.getConnection(url, uname, pwd);
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doView(HttpSession session) {
		String query = "select * from Order where `userid`=?";

		try {

			PreparedStatement ps = this.connection.prepareStatement(query);
			ps.setString(0, (String) session.getAttribute("userId"));
			this.results = ps.executeQuery();

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

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		table += "</table>";
		return table;
	}

}
