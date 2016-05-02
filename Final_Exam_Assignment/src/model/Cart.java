package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import dbhelpers.CartHelper;

public class Cart {
	private int userId;
	private HashMap<Integer, Integer> productsInCart;
	private int totalPrice = 0;

	/**
	 * @return the productsInCart
	 */
	public HashMap<Integer, Integer> getProductsInCart() {
		return productsInCart;
	}

	/**
	 * @param productsInCart
	 *            the productsInCart to set
	 */
	public void setProductsInCart(HashMap<Integer, Integer> productsInCart) {
		this.productsInCart = productsInCart;
	}

	public Cart(int userId) {

		CartHelper cartHelper = new CartHelper();
		this.setUserId(userId);
		ResultSet productsRS = cartHelper.getCartForUser(userId);
		this.productsInCart = new HashMap<Integer, Integer>();
		try {
			while (productsRS.next()) {
				int productId = productsRS.getInt("productId");
				int quantity = productsRS.getInt("quantity");
				Product product = new Product(productId);
				totalPrice += product.getPrice() * quantity;
				productsInCart.put(productId, quantity);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			cartHelper.close();
		}

	}

	public boolean addProducts(int productId, int quantity) {
		boolean success = false;
		Integer currentQuantity = productsInCart.get(productId);
		if (currentQuantity == null)
			this.productsInCart.put(productId, quantity);
		else {
			this.productsInCart.put(productId, currentQuantity + quantity);
			Product product = new Product(productId);
			// subtract quantity from product
			// save
		}
		CartHelper cartHelper = new CartHelper();
		cartHelper.saveCartChanges(this.userId, this.productsInCart);
		return success;
	}

	public boolean removeProduct(int productId) {
		boolean success = false;
		Integer currentQuantity = productsInCart.get(productId);
		if (currentQuantity == null) {
			return success;
		} else {
			this.productsInCart.remove(productId);
		}
		CartHelper cartHelper = new CartHelper();
		cartHelper.saveCartChanges(this.userId, this.productsInCart);
		return success;
	}

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the totalPrice
	 */
	public int getTotalPrice() {
		return totalPrice;
	}

	/**
	 * @param totalPrice
	 *            the totalPrice to set
	 */
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

}
