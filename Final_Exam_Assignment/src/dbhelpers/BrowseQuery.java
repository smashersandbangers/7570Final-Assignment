package dbhelpers;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

import model.Product;

public class BrowseQuery {
	private Connection connection;
	private ResultSet results;

	public BrowseQuery(String dbName, String uname, String pwd) {
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

	public void doBrowse() {
		String query = "select * from Product";
		try {
			PreparedStatement ps = this.connection.prepareStatement(query);
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
				Product product = new Product();

				product.setName(this.results.getString("name"));
				product.setDesc(this.results.getString("desc"));
				product.setImg(this.results.getString("img"));
				product.setPrice(this.results.getInt("price"));
				product.setQuantity(this.results.getInt("quantity"));
				product.setId(this.results.getInt("id"));

				table += "<tr>";
				table += "<td>";
				table += product.getName();
				table += "</td>";
				table += "<td><img src='";
				table += product.getImg();
				table += "' height='100' width='100'>";
				table += "</td>";
				table += "<td>";
				table += product.getDesc();
				table += "</td>";
				table += "<td>";
				table += "$";
				table += product.getPrice();
				table += "</td>";
				table += "<td>";
				table += product.getQuantity();
				table += "</td>";

				table += "<td>";
				// table += "<form action=\"Add\" method=\"POST\"> <input
				// type=\"text\" name=\"quantity\" required value=\"0\"> <input
				// type=\"hidden\" name=\"productId\" value=\""
				// + product.getId() + "\"> <input type=\"submit\"
				// value=\"Add\">";
				table += "<a href=Add?productId=" + product.getId() + ">Add To Cart</a> ";

				table += "</td>";
				table += "</tr>";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		table += "</table>";
		return table;
	}

}
