/**
 * 
 */
package dbhelpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author mathewalexander
 *
 */
public class DeleteQuery {
	private Connection connection;

	public DeleteQuery(String dbName, String uname, String pwd) {
		String url = "jdbc:mysql://localhost:3306/" + dbName;

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			this.connection = DriverManager.getConnection(url, uname, pwd);

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void doDelete(String SKU) {
		// String to hold query
		String query = "delete from `Order` where productid = ?";
		
		// create a prepared Statement
		try {
			PreparedStatement ps = connection.prepareStatement(query);

			// fill in the prepared statement
			ps.setString(1, query);

			// execute query
			ps.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
