package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Favs;
import model.Product;

public class FavDAO {
	private ProductDAO productDAO = new ProductDAO();

	public void addToFavs(int userId,int product_id) {
		// TODO Auto-generated method stub
		
		String sql = "Insert into Favorites(user_id,product_id) VALUES(?,?) ";
		try(Connection conn = DatabaseConnection.getConnection();
				PreparedStatement sts = conn.prepareStatement(sql);){
			sts.setInt(1, userId);
			sts.setInt(2, product_id);
			sts.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

	public List<Product> getAllFavs(int userId) {
		// TODO Auto-generated method stub
		List<Product> Favitems = new ArrayList<>();
		String query = "SELECT * from Favorites where user_id = ?";
		try(Connection conn = DatabaseConnection.getConnection();
				PreparedStatement st = conn.prepareStatement(query)){
			st.setInt(1, userId);
			ResultSet rs = st.executeQuery();
	        // Process the result set
				while (rs.next()) {
					Product product = productDAO.getproductdetailsbyId(rs.getInt("product_id"));
					Favitems.add(product);
	        }
				
		}catch (SQLException e) {
            e.printStackTrace();
        }
		return Favitems;
	}

	public void removeFromFavs(int userId, int product_id) {
		// TODO Auto-generated method stub
		String query = "SELECT * from Favorites where user_id = ?";
		try(Connection conn = DatabaseConnection.getConnection();
				PreparedStatement st = conn.prepareStatement(query)){
			st.setInt(1, userId);
			ResultSet rs = st.executeQuery();
	        // Process the result set
				while (rs.next()) {
					productDAO.getproductdetailsbyId(rs.getInt("product_id"));
	        }
				
		}catch (SQLException e) {
            e.printStackTrace();
        }
//		int item = InputUtil.getInt("Enter number to remove: ");
		String sql = "DELETE from Favorites where product_id = ? AND user_id = ? ";
		try(Connection conn = DatabaseConnection.getConnection();
				PreparedStatement st = conn.prepareStatement(sql)){
			st.setInt(1, product_id);
			st.setInt(2, userId);
			st.executeUpdate();
				
		}catch (SQLException e) {
            e.printStackTrace();
        }
	}
	
	
	
}
