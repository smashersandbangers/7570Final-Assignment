package dbhelpers;

import static dbhelpers.Constants.dbName;
import static dbhelpers.Constants.pwd;
import static dbhelpers.Constants.uname;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderHelper {

	private Connection connection;

	/**
	 * Constructor which makes a connection
	 */

	public OrderHelper() {

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

	public ResultSet getOrderInfo(int id) {
		ResultSet resultSet = null;
		String query = "select * from Order where id=?";
		try {
			PreparedStatement ps = this.connection.prepareStatement(query);
			ps.setInt(1, id);
			resultSet = ps.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resultSet;
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
