package model;

public class Product {
	private String name;
	private String description;
	private String img_url;
	private double price;
	private double dis_price;
	private int quantity;
	private int thres_quanty;
	private int product_id;
	private int category_id;
	private int seller_id;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getImg_url() {
		return img_url;
	}
	public void setImg_url(String img_url) {
		this.img_url = img_url;
	}
	public double getDis_price() {
		return dis_price;
	}
	public void setDis_price(double dis_price) {
		this.dis_price = dis_price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getThres_quanty() {
		return thres_quanty;
	}
	public void setThres_quanty(int thres_quanty) {
		this.thres_quanty = thres_quanty;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public int getCategory_id() {
		return category_id;
	}
	public void setCategory_id(int category_id) {
		this.category_id = category_id;
	}
	public int getSeller_id() {
		return seller_id;
	}
	public void setSeller_id(int seller_id) {
		this.seller_id = seller_id;
	}

	
	

}
