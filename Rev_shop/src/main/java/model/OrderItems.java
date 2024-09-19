package model;

public class OrderItems {
	private int order_id;
	private int product_id;
	private int quantity;
	private double price_at_purchase;
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice_at_purchase() {
		return price_at_purchase;
	}
	public void setPrice_at_purchase(double price_at_purchase) {
		this.price_at_purchase = price_at_purchase;
	}
}
