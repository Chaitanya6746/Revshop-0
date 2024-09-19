package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Category;
import util.InputUtil;


public class CategoryDAO {
	
	public List<Category> displayCategories() {
		List<Category> categorys = new ArrayList<>();
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
        	categorys.add(category);	
        }
			
    } catch (Exception e) {
        e.printStackTrace();
    }
		return categorys;
}
}
