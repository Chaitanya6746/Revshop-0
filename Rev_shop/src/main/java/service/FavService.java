package service;

import java.util.List;

import dao.FavDAO;
import model.Product;

public class FavService {
	private FavDAO favDAO = new FavDAO();

	public void addTofavs(int userId,int product_id) {
		// TODO Auto-generated method stub
		favDAO.addToFavs(userId,product_id);
	}

	public List<Product> showfavs(int userId) {
		// TODO Auto-generated method stub
		return favDAO.getAllFavs(userId);
	}

	public void removeFavs(int userId, int product_id) {
		// TODO Auto-generated method stub
		favDAO.removeFromFavs(userId,product_id);
	}
	
	
}
