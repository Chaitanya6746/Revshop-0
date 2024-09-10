package service;

import dao.CartDAO;
import model.Cart;

public class CartService {
	private CartDAO cartDAO = new CartDAO();
	

	public void addToCart(int user_id,int product_id,Cart cart) {
		// TODO Auto-generated method stub
		cartDAO.addtoCart(user_id,product_id,cart);
	}

	public void removeFromCart(int user_id) {
		// TODO Auto-generated method stub
		cartDAO.removeproduct(user_id);
		
	}

	public void showCartItems(int user_id) {
		// TODO Auto-generated method stub
		cartDAO.showItems(user_id);
		
	}

}
