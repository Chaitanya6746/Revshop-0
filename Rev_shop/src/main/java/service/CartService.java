package service;

import java.util.List;

import dao.CartDAO;
import model.Cart;
import model.Product;

public class CartService {
	private CartDAO cartDAO = new CartDAO();
	

	public void addToCart(int user_id,int product_id,Cart cart) {
		// TODO Auto-generated method stub
		cartDAO.addtoCart(user_id,product_id,cart);
	}

	public void removeFromCart(int user_id,int product_id) {
		// TODO Auto-generated method stub
		cartDAO.removeproduct(user_id,product_id);
		
	}
	public int getQuantfromCart(int product_id) {
		return cartDAO.getquant(product_id);
	}

	public List<Product> showCartItems(int user_id) {
		// TODO Auto-generated method stub
		return cartDAO.showItems(user_id);
		
	}

}
