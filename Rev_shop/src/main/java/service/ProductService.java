package service;

import java.util.List;

import dao.ProductDAO;
import exception.InvalidInputException;
import model.Product;
import model.Seller;

public class ProductService {
	private ProductDAO productDAO = new ProductDAO();

	public Product addProduct(int id,int category_id, Product product) throws InvalidInputException {
		// TODO Auto-generated method stub
		if (product.getName().isEmpty() || product.getDescription().isEmpty()|| product.getImg_url().isEmpty()) {
            throw new InvalidInputException("All fields are required.");
        }
		productDAO.addProductTOInventory(id,category_id,product);
		return product;
	}
	public List<Product> showAllProducts() {
		// TODO Auto-generated method stub
		return productDAO.getAllproducts();
		
	}
	public List<Product> getProductDetails(int cat_id) {
		// TODO Auto-generated method stub
		return productDAO.getAllproductsBycategory(cat_id);
		
	}

	public Product getProductDetailsbyId(int product_id) {
		// TODO Auto-generated method stub
		return productDAO.getproductdetailsbyId(product_id);
	}
	
	public List<Product> getproductBySellerId(int seller_id){
		return productDAO.getProductsByseller(seller_id);
		
	}
	public void updateInventoryById(int product_id,Product product) {
		productDAO.updateProductbyId(product_id,product);
	}
	
	public void updateInventorybyprice(int product_id,double price) {
		// TODO Auto-generated method stub
		productDAO.updateProductbyprice(product_id,price);
	}
	public void updateInventorybyquant(int product_id, int quantity) {
		// TODO Auto-generated method stub
		productDAO.updateByquant(product_id,quantity);
	}
	public void updateInventorybydis_price(int product_id,double dis_price) {
		// TODO Auto-generated method stub
		productDAO.updateProductbydis_price(product_id,dis_price);
	}
	public void removeProductById(int product_id) {
		// TODO Auto-generated method stub
		productDAO.removeById(product_id);
	}

}
