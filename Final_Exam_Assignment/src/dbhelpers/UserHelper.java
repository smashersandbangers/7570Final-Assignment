package dbhelpers;

import static dbhelpers.Constants.dbName;
import static dbhelpers.Constants.pwd;
import static dbhelpers.Constants.uname;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

//helper class to insert, retrieve, update user table

public class UserHelper {

	/**
	 * Prepared SQL statement (combats: SQL Injections)
	 */
	private PreparedStatement authQuery;
	private Connection connection;

	/**
	 * Constructor which makes a connection
	 */
	public UserHelper() {

		String url = "jdbc:mysql://localhost:3306/" + dbName;

		// setting up driver

		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			this.connection = DriverManager.getConnection(url, uname, pwd);

		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// authenticateUser = conn.prepareStatement("select * from `User` where
		// `username`=? and `password`=?");

	}

	/**
	 * Authenticates a user in the database.
	 * 
	 * @param username
	 *            The username for the user.
	 * @param password
	 *            The password for the user.
	 * @return A user object if successful, null if unsuccessful.
	 */
	public User doAuthenticate(User user) {

		try {
			System.out.println("username:" + user.getUsername());

			String authQuery = "SELECT id, name, username from User where username = ? and password = ?";

			PreparedStatement psAuth = connection.prepareStatement(authQuery);
			psAuth.setString(1, user.getUsername());
			psAuth.setString(2, user.getPassword());
			ResultSet rs = psAuth.executeQuery();

			// if we've returned a row, turn that row into a new user object

			if (rs.next()) {
				user.setUserid(rs.getInt("id"));
				user.setName(rs.getString("name"));
			}

		} catch (SQLException e) {
			System.out.println(e.getClass().getName() + ":" + e.getMessage());

		}
		System.out.println(user);
		return user;
	}

	// Query to add user to the USer table

	public boolean doAdd(User user) {
		boolean success = false;
		String uniquenessQuery = "SELECT id, name, username from User where username = ?";

		String insertQuery = "INSERT INTO `User`(`name`,`username`,`password`) values (?, ?, ?)";

		try {
			PreparedStatement psUnique = connection.prepareStatement(uniquenessQuery);
			psUnique.setString(1, user.getUsername());
			ResultSet rs = psUnique.executeQuery();
			if (rs.next()) {

			} else {
				PreparedStatement psInsert = connection.prepareStatement(insertQuery);

				psInsert.setString(1, user.getName());
				psInsert.setString(2, user.getUsername());
				psInsert.setString(3, user.getPassword());

				psInsert.executeUpdate();
				success = true;
			}
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

	public PreparedStatement getAuthQuery() {
		return authQuery;
	}

	public void setAuthQuery(PreparedStatement authQuery) {
		this.authQuery = authQuery;
	}

}
