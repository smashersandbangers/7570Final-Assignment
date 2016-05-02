package dbhelpers;

import static dbhelpers.Constants.dbName;
import static dbhelpers.Constants.pwd;
import static dbhelpers.Constants.uname;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;

public class CartHelper {

	private Connection connection;

	/**
	 * Constructor which makes a connection
	 */

	public CartHelper() {

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

	public ResultSet getCartForUser(int userId) {
		ResultSet resultSet = null;
		String query = "select `productId`, `quantity` from Cart where userId=?";
		try {
			PreparedStatement ps = this.connection.prepareStatement(query);
			ps.setInt(1, userId);
			resultSet = ps.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultSet;
	}

	public boolean saveCartChanges(int userId, HashMap<Integer, Integer> productsInCart) {
		boolean success = false;

		String deleteQuery = "DELETE FROM `Cart` WHERE `userId`=?";

		String insertQuery = "INSERT INTO `Cart`(`userId`,`productId`,`quantity`) values (?, ?, ?)";
		try {
			PreparedStatement psDelete = this.connection.prepareStatement(deleteQuery);
			psDelete.setInt(1, userId);
			psDelete.executeUpdate();

			PreparedStatement ps = this.connection.prepareStatement(insertQuery);
			ps.setInt(1, userId);
			Iterator<Integer> it = productsInCart.keySet().iterator();
			while (it.hasNext()) {
				int productId = it.next();
				ps.setInt(2, productId);
				ps.setInt(3, (int) productsInCart.get(productId));
				ps.executeUpdate();
			}

			success = true;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return success;
	}

	public void close() {
		if (connection != null)
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

}
