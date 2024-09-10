package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Seller;

public class SellerDAO {

	public void addSeller(Seller seller) {
		// TODO Auto-generated method stub
		String query = "INSERT INTO Sellers(seller_id,business_name,business_address,registration_date) VALUES(?,?,?,CURRENT_DATE())";
		try (Connection conn = DatabaseConnection.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(query)) {
				stmt.setInt(1, seller.getSellerId());
	            stmt.setString(2, seller.getBusiness_name());
	            stmt.setString(3, seller.getBus_adress());
	            stmt.executeUpdate();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		
	}
	
	public Seller getBusinessDetailsById(int seller_id){
		// TODO Auto-generated method stub
		String query = "SELECT * FROM sellers WHERE seller_id = ?";
		try (Connection conn = DatabaseConnection.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(query)) {
			
			
			stmt.setInt(1, seller_id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				Seller seller = new Seller();
                seller.setSellerId(rs.getInt("seller_id"));
                seller.setBusinessName(rs.getString("business_name"));
                seller.setBusinessAdress(rs.getString("business_address"));
                return seller;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return null;
	}
	

	public  boolean checkSeller(int seller_id) {
		
		// TODO Auto-generated method stub
		String query = "SELECT COUNT(*) FROM sellers where seller_id =?";
		try (Connection conn = DatabaseConnection.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(query)) {
				stmt.setInt(1,seller_id);
				return true;	
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		
		return false;
	}

}
