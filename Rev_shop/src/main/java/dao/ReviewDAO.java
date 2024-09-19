package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Product;
import model.Review;

public class ReviewDAO {

	public void AddReview(int userId, int productId, int rating, String comment) {
		// TODO Auto-generated method stub
		String query = "INSERT into Reviews(user_id,product_id,rating,comment) VALUES(?,?,?,?)";
		try(Connection conn = DatabaseConnection.getConnection();
				PreparedStatement st = conn.prepareStatement(query)){
			st.setInt(1, userId);
			st.setInt(2, productId);
			st.setInt(3, rating);
			st.setString(4, comment);
			st.executeUpdate();
		}catch (SQLException e) {
            e.printStackTrace();
        }
	}
	public boolean hasUserPurchasedProduct(int userId, int productId) {
        String query = "SELECT COUNT(*) FROM Order_Items oi "
                     + "JOIN Orders o ON oi.order_id = o.order_id "
                     + "WHERE o.user_id = ? AND oi.product_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, userId);
            ps.setInt(2, productId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
	public Review getreviewbyId(int product_id) {
		// TODO Auto-generated method stub
		String query = "SELECT * from Reviews where product_id = ?";
		try(Connection conn = DatabaseConnection.getConnection();
				PreparedStatement st = conn.prepareStatement(query)){
			st.setInt(1, product_id);
			ResultSet rs = st.executeQuery();
	        // Process the result set
				while (rs.next()) {
					Review review = new Review();
					review.setComment(rs.getString("comment"));
					review.setRating(rs.getInt("rating"));
					return review;
					
	        }
				
		}catch (SQLException e) {
            e.printStackTrace();
        }
		return null;
		
	}
	public List<Review> getReviews() {
		// TODO Auto-generated method stub
		List<Review> reviews = new ArrayList<>();
		String sql = "select * from Reviews";
		try(Connection conn = DatabaseConnection.getConnection();
				PreparedStatement st = conn.prepareStatement(sql)){
			ResultSet rs = st.executeQuery();
	        // Process the result set
				while (rs.next()) {
					Review review = new Review();
					review.setReviewId(rs.getInt("review_id"));
					review.setProductId(rs.getInt("product_id"));
					review.setUserId(rs.getInt("user_id"));
					review.setComment(rs.getString("comment"));
					review.setRating(rs.getInt("rating"));
					review.setReviewDate(rs.getTimestamp("review_date"));
					reviews.add(review);
	        }
				
		}catch (SQLException e) {
            e.printStackTrace();
        }
		return reviews;
	}
	
}
