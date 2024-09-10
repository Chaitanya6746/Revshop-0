package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

public class UserDAO {

	public void addUser(User user){
		// TODO Auto-generated method stub
		String query = "INSERT INTO users(name,email,password,role,address,phone_number) VALUES(?,?,?,?,?,?) ";
		try(Connection conn = DatabaseConnection.getConnection();
			PreparedStatement st = conn.prepareStatement(query)){
			
			st.setString(1, user.getUsername());
            st.setString(2, user.getEmail());
            st.setString(3, user.getPassword());
            st.setString(4, user.getRole());
            st.setString(5, user.getAddress());
            st.setLong(6, user.getPhone_num());
            st.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		
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

}
