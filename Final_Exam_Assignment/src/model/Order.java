package model;

import java.sql.ResultSet;
import java.sql.SQLException;

import dbhelpers.OrderHelper;

public class Order {

	private int orderid;
	private int productid;
	private int userid;
	private int quantity;
	private int total;

	/**
	 * @param orderid
	 * @param productid
	 * @param userid
	 * @param quantity
	 * @param total
	 */
	public Order(int orderid, int productid, int userid, int quantity, int total) {
		this.orderid = orderid;
		this.productid = productid;
		this.userid = userid;
		this.quantity = quantity;
		this.total = total;
	}

	public Order(int orderid) {
		this.orderid = orderid;
		OrderHelper orderHelper = new OrderHelper();
		ResultSet rs = orderHelper.getOrderInfo(orderid);
		try {
			if (rs.next()) {

				this.quantity = rs.getInt("Quantity");
				this.total = rs.getInt("total");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * 
	 */
	public Order() {

		// orderItems = new HashMap<>();

	}

	/**
	 * @return the orderid
	 */
	public int getOrderid() {
		return orderid;
	}

	/**
	 * @param orderid
	 *            the orderid to set
	 */
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}

	/**
	 * @return the productid
	 */
	public int getProductid() {
		return productid;
	}

	/**
	 * @param productid
	 *            the productid to set
	 */
	public void setProductid(int productid) {
		this.productid = productid;
	}

	/**
	 * @return the userid
	 */
	public int getUserid() {
		return userid;
	}

	/**
	 * @param userid
	 *            the userid to set
	 */
	public void setUserid(int userid) {
		this.userid = userid;
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
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}

	/**
	 * @param total
	 *            the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}

}
