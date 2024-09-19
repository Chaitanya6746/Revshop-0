package service;

import java.util.List;

import dao.ReviewDAO;
import model.Review;

public class ReviewService {
	private ReviewDAO reviewDAO = new ReviewDAO();

	public void addReview(int userId, int productId, int rating, String comment) {
		// TODO Auto-generated method stub
		reviewDAO.AddReview(userId,productId,rating,comment);
	}

	public Review showAllReviews(int product_id) {
		// TODO Auto-generated method stub
		return reviewDAO.getreviewbyId(product_id);
	}
	
	public boolean Checkreview(int user_id,int product_id) {
		// TODO Auto-generated method stub
		return reviewDAO.hasUserPurchasedProduct(user_id,product_id);
	}

	public List<Review> getAllReview() {
		return reviewDAO.getReviews();
		// TODO Auto-generated method stub
		
	}
}
