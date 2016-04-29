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
	
	
public BrowseQuery(String dbName, String uname, String pwd){
	 String url ="jdbc:mysql://localhost:3306/"+ dbName;
	 
	 // set up driver 
	 
	try {
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		this.connection = (Connection) DriverManager.getConnection(url,uname,pwd);
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
public void doBrowse(){
	String query ="select * from Product";
	try {
		PreparedStatement ps = this.connection.prepareStatement(query);
		this.results=ps.executeQuery();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

public String getHTMLTable(){
String table ="";


try {
	while (this.results.next()){
	Product product = new Product();
	
		product.setId(this.results.getInt("id"));
		product.setName(this.results.getString("name"));
		product.setDesc(this.results.getString("desc"));
		product.setImg(this.results.getString("img"));
		product.setPrice(this.results.getInt("price"));
		product.setQuantity(this.results.getInt("quantity"));
		
		table+= "<tr>";
		table+= "<td>";		
		table+=	product.getId();
		table+= "</td>";
		table+= "<td>";	
		table+=	product.getName();
		table+= "</td>";
		table+= "<td>";	
		table+=	product.getImg();
		table+= "</td>";
		table+= "<td>";	
		table+=	product.getDesc();
		table+= "</td>";
		table+= "<td>";	
		table+=	product.getPrice();
		table+= "</td>";
		table+= "<td>";	
		table+=	product.getQuantity();
		table+= "</td>";
			
				
			
				table+= "<td>";	
				table+= "<a href=Add?id=" + product.getId() + ">Add to Cart</a>";
				table+="</tr>";
	}
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

table+="</table>";
return table;
}

}
