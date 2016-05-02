package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import dbhelpers.ProductHelper;

public class Product {

	private int id;
	private String name;
	private String img;
	private double price;
	private int quantity;
	private String desc;

	/**
	 * @param id
	 * @param name
	 * @param img
	 * @param price
	 * @param quantity
	 */
	public Product(int id, String name, String img, double price, int quantity, String desc) {
		this.id = id;
		this.name = name;
		this.img = img;
		this.price = price;
		this.quantity = quantity;
		this.desc = desc;
	}

	public Product(int id) {
		this.id = id;
		ProductHelper productHelper = new ProductHelper();
		ResultSet rs = productHelper.getProductInfo(id);
		try {
			if (rs.next()) {
				this.name = rs.getString("Name");
				this.img = rs.getString("Img");
				this.price = rs.getInt("Price");
				this.quantity = rs.getInt("Quantity");
				this.desc = rs.getString("Desc");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public Product() {

	}

	// Generating getters and setters

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
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

	/**
	 * @return the img
	 */
	public String getImg() {
		return img;
	}

	/**
	 * @param img
	 *            the img to set
	 */
	public void setImg(String img) {
		this.img = img;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price
	 *            the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity
	 *            the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc
	 *            the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", img=" + img + ", price=" + price + ", quantity=" + quantity
				+ ", desc=" + desc + "]";
	}

}
