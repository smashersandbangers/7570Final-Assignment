package model;

import dbhelpers.UserHelper;

public class User {

	public int id;
	private String username;
	private String password;
	private String name;

	/**
	 * @param userid
	 * @param name
	 * @param age
	 * @param username
	 * @param password
	 */

	// empty constructor
	public User() {
	}

	public User(int id, String username, String password) {
		this.id = id;
		this.username = username;
		this.password = password;

	}

	/**
	 * @return the userid
	 */
	public int getUserid() {
		return id;
	}

	/**
	 * @param userid
	 *            the userid to set
	 */
	public void setUserid(int userid) {
		this.id = userid;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
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
	 * @param password
	 *            the password to set
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
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name + "]";
	}

	public boolean save() {
		boolean success = false;
		UserHelper userHelper = new UserHelper();
		try {
			System.out.println(this.getName());
			System.out.println(this.getUsername());
			System.out.println(this.getPassword());
			success = userHelper.doAdd(this);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			userHelper.close();
		}
		return success;
	}

	public User authenticate() {

		UserHelper userHelper = new UserHelper();
		try {
			System.out.println(this.getName());
			System.out.println(this.getUsername());
			System.out.println(this.getPassword());
			userHelper.doAuthenticate(this);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			userHelper.close();
		}
		return this;
	}

}