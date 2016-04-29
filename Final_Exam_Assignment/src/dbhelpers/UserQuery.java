package dbhelpers;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

import model.User;

//helper class to insert, retrieve, update user table

public class UserQuery {

	/**
	 * Prepared SQL statement (combats: SQL Injections)
	 */
	private PreparedStatement authenticateUser;
	
	/**
	 * Constructor which makes a connection
	 */
	public UserQuery() {
		try {
			//Set up connection
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/Cricket_Store_Database", "root", "password");
			
			//Create the preparedstatement(s)
			authenticateUser = conn.prepareStatement("select * from User where `username`=? and `password`=?");
		} catch (Exception e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}
	}
	
	/**
	 * Authenticates a user in the database.
	 * @param username  The username for the user.
	 * @param password  The password for the user.
	 * @return A user object if successful, null if unsuccessful.
	 */
	public User authenticateUser(int userid, int age, String name,String username, String password) {
		User user = null;
		try {
			//Add parameters to the ?'s in the preparedstatement and execute
			authenticateUser.setString(1, username);
			authenticateUser.setString(2, password);
			ResultSet rs = authenticateUser.executeQuery();
			
			//if we've returned a row, turn that row into a new user object
			
			
			if (rs.next()) {
				user = new User(rs.getInt(userid),rs.getString("name"),rs.getInt(age),rs.getString("username"), rs.getString("password"));
			}
		} catch (SQLException e) {
			System.out.println(e.getClass().getName() + ": " + e.getMessage());
		}
		return user;
	}
}










