package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Cart;
//import model.Product;
import util.InputUtil;


public class CartDAO {
	private ProductDAO productDAO = new ProductDAO();

	public void addtoCart(int user_id,int product_id,Cart cart) {
		// TODO Auto-generated method stub
		String query = "INSERT into cart(user_id,product_id,quantity) VALUES(?,?,?)";
		try(Connection conn = DatabaseConnection.getConnection();
				PreparedStatement st = conn.prepareStatement(query)){
			st.setInt(1, user_id);
			st.setInt(2, product_id);
			st.setInt(3, cart.getQuantity());
			st.executeUpdate();
		}catch (SQLException e) {
            e.printStackTrace();
        }
	}

	public void removeproduct(int user_id) {
		// TODO Auto-generated method stub
		String query = "SELECT * from Cart where user_id = ?";
		try(Connection conn = DatabaseConnection.getConnection();
				PreparedStatement st = conn.prepareStatement(query)){
			st.setInt(1, user_id);
			ResultSet rs = st.executeQuery();
	        // Process the result set
				while (rs.next()) {
					productDAO.getproductdetailsbyId(rs.getInt("product_id"));
	        }
				
		}catch (SQLException e) {
            e.printStackTrace();
        }
		int item = InputUtil.getInt("Enter number to remove: ");
		String sql = "DELETE from Cart where product_id = ? AND user_id = ? ";
		try(Connection conn = DatabaseConnection.getConnection();
				PreparedStatement st = conn.prepareStatement(sql)){
			st.setInt(1, item);
			st.setInt(2, user_id);
			st.executeUpdate();
				
		}catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

	public void showItems(int user_id) {
		// TODO Auto-generated method stub
		String query = "SELECT * from Cart where user_id = ?";
		try(Connection conn = DatabaseConnection.getConnection();
				PreparedStatement st = conn.prepareStatement(query)){
			st.setInt(1, user_id);
			ResultSet rs = st.executeQuery();
	        // Process the result set
				while (rs.next()) {
					productDAO.getproductdetailsbyId(rs.getInt("product_id"));
	        }
				
		}catch (SQLException e) {
            e.printStackTrace();
        }
	}
		
}
