package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.xdevapi.Statement;

import model.Seller;
import model.User;

public class UserDAO {
		// TODO Auto-generated method stub
		public int addUser(User user) throws SQLException {
	        String sql = "INSERT INTO users (name, email, password, role, address, phone_number) VALUES (?, ?, ?, ?, ?, ?)";
	        try (Connection conn = DatabaseConnection.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
	            stmt.setString(1, user.getUsername());
	            stmt.setString(2, user.getEmail());
	            stmt.setString(3, user.getPassword());
	            stmt.setString(4, user.getRole());
	            stmt.setString(5, user.getAddress());
	            stmt.setLong(6, user.getPhone_num());

	            int affectedRows = stmt.executeUpdate();
	            if (affectedRows == 0) {
	                throw new SQLException("Creating user failed, no rows affected.");
	            }

	            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
	                if (generatedKeys.next()) {
	                    user.setUserId(generatedKeys.getInt(1));
	                } else {
	                    throw new SQLException("Creating user failed, no ID obtained.");
	                }
	            }
	        }
	        return user.getUserId();
	    }


	public static User getUserById(int id) {
		// TODO Auto-generated method stub
		String query = "SELECT * FROM users where user_id= ?";
		try(Connection conn = DatabaseConnection.getConnection();
				PreparedStatement st = conn.prepareStatement(query)){
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				User user = new User();
				user.setUserId(rs.getInt("user_id"));
				user.setUsername(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setRole(rs.getString("role"));
				return user;
			}
			
		}catch(SQLException e) {
			
		}
		return null;
	}

	public static User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		String query = "SELECT * FROM users where name= ?";
		try(Connection conn = DatabaseConnection.getConnection();
				PreparedStatement st = conn.prepareStatement(query)){
			st.setString(1, username);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				User user = new User();
				user.setUserId(rs.getInt("user_id"));
				user.setUsername(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getString("role"));
				return user;
			}
			
		}catch(SQLException e) {
			
		}
		return null;
	}
	public static User getUserBymail(String email) {
		// TODO Auto-generated method stub
		String query = "SELECT * FROM users where email= ?";
		try(Connection conn = DatabaseConnection.getConnection();
				PreparedStatement st = conn.prepareStatement(query)){
			st.setString(1, email);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				User user = new User();
				user.setUserId(rs.getInt("user_id"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setRole(rs.getString("role"));
				return user;
			}
			
		}catch(SQLException e) {
			
		}
		return null;
	}

}
