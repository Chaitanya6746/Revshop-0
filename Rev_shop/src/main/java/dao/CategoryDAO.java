package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import model.Category;
import util.InputUtil;


public class CategoryDAO {
	
	public int displayCategories() {
		String sql = "SELECT * FROM Categories";
		try(Connection conn = DatabaseConnection.getConnection();
			Statement st = conn.createStatement();){
			ResultSet rs = st.executeQuery(sql);
        // Process the result set
			while (rs.next()) {
        	Category category = new Category();
        	category.setCategory_id(rs.getInt("category_id"));
        	category.setCategory_name(rs.getString("category_name"));
        	System.out.println(category.getCategory_id()+" "+category.getCategory_name());
        	
        }
			int category_id = InputUtil.getInt("Choose Category: ");
			return category_id;
			
    } catch (Exception e) {
        e.printStackTrace();
	}
		return 0;
}
}
