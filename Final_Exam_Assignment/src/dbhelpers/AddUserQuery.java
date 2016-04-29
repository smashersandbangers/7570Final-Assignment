package dbhelpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.User;

public class AddUserQuery {

	public Connection connection;

	public AddUserQuery(String dbName, String uname, String pwd){
		 String url ="jdbc:mysql://localhost:3306/"+ dbName;
		 
		 //setting up driver
		 
		 try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			this.connection = DriverManager.getConnection(url,uname,pwd);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
	}
	
// Query the DB to add products
	
	public void doAdd(User user){
		String query="insert into `User`( `userid`,`Name`,`Age`,`username`,`password`) values (?, ?, ?, ?,?) where `User`.`userid`=?";
		
		try {
			PreparedStatement ps=connection.prepareStatement(query);
			ps.setInt(1,user.getUserid());
			ps.setString(2,user.getName());
			ps.setInt(3, user.getAge());
			ps.setString(4,user.getUsername());
			ps.setString(5,user.getPassword());
			
			ps.executeUpdate();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
}

	
	

