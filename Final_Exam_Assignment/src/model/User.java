package model;

public class User {
	
public int userid;
private String username;
private String password;
private String name;
private int age;
/**
 * @param userid
 * @param name
 * @param age
 * @param username
 * @param password
 */


public User(int userid, String username, String password, String name, int age) {
	this.userid = 0;
	this.username = username;
	this.password = password;
	this.name=name;
	this.age = age;
}





/**
 * @return the userid
 */
public int getUserid() {
	return userid;
}

/**
 * @param userid the userid to set
 */
public void setUserid(int userid) {
	this.userid = userid;
}



/**
 * @return the username
 */
public String getUsername() {
	return username;
}

/**
 * @param username the username to set
 */
public void setUsername(String username) {
	this.username = username;
}

/**
 * @return the password
 */
public String getPassword() {
	return password;
}

/**
 * @param password the password to set
 */
public void setPassword(String password) {
	this.password = password;
}

/**
 * @return the name
 */
public String getName() {
	return name;
}

/**
 * @param name the name to set
 */
public void setName(String name) {
	this.name = name;
}

/**
 * @return the age
 */
public int getAge() {
	return age;
}

/**
 * @param age the age to set
 */
public void setAge(int age) {
	this.age = age;
}

/* (non-Javadoc)
 * @see java.lang.Object#toString()
 */
@Override
public String toString() {
	return "User [userid=" + userid + ", username=" + username + ", password=" + password + ", name=" + name + ", age="
			+ age + "]";
}
	
	
	
}