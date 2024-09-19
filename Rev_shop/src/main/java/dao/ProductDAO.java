package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Category;
import model.Product;
//import model.Seller;
//import model.User;

public class ProductDAO {
	

	public void addProductTOInventory(int id,int category_id,Product product) {
		String query = "INSERT into products(seller_id,category_id,name,description,image_url,price,discount_price,quantity_available,threshold_quantity) VALUES(?,?,?,?,?,?,?,?,?)";
		try(Connection conn = DatabaseConnection.getConnection();
				PreparedStatement st = conn.prepareStatement(query)){
			st.setInt(1, id);
			st.setInt(2, category_id);
			st.setString(3, product.getName());
			st.setString(4, product.getDescription());
			st.setString(5,product.getImg_url());
			st.setDouble(6, product.getPrice());
			st.setDouble(7, product.getDis_price());
			st.setInt(8, product.getQuantity());
			st.setInt(9, product.getThres_quanty());
			st.executeUpdate();
		}catch (SQLException e) {
            e.printStackTrace();
        }
	}
	public void addProductTocart(int id,int category_id,Product product) {
		String query = "INSERT into products(seller_id,category_id,name,description,image_url,price,discount_price,quantity_available,threshold_quantity) VALUES(?,?,?,?,?,?,?,?,?)";
		try(Connection conn = DatabaseConnection.getConnection();
				PreparedStatement st = conn.prepareStatement(query)){
			st.setInt(1, id);
			st.setInt(2, category_id);
			st.setString(3, product.getName());
			st.setString(4, product.getDescription());
			st.setString(5,product.getImg_url());
			st.setDouble(6, product.getPrice());
			st.setDouble(7, product.getDis_price());
			st.setInt(8, product.getQuantity());
			st.setInt(9, product.getThres_quanty());
			st.executeUpdate();
		}catch (SQLException e) {
            e.printStackTrace();
        }
	}

	
		public List<Product> getAllproducts() {
			// TODO Auto-generated method stub
			List<Product> products = new ArrayList<>();
			String sql = "SELECT * FROM products";
			try(Connection conn = DatabaseConnection.getConnection();
				Statement st = conn.createStatement();){
				ResultSet rs = st.executeQuery(sql);
	        // Process the result set
				while (rs.next()) {
	        	Product product = new Product();
	        	product.setProduct_id(rs.getInt("product_id"));
	        	product.setName(rs.getString("name"));
	        	product.setDescription(rs.getString("description"));
	        	product.setImg_url(rs.getString("image_url"));
	        	product.setPrice(rs.getDouble("price"));
	        	product.setDis_price(rs.getDouble("discount_price"));
	        	product.setQuantity(rs.getInt("quantity_available"));
	        	System.out.println(product.getProduct_id()+" "+product.getName()+" "
	        			+product.getDescription()+" "+product.getImg_url()+" "+product.getPrice()
	        			+" "+product.getQuantity());
	        	products.add(product);
	        }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			return products;
	}
			
			public List<Product> getAllproductsBycategory(int id) {
				// TODO Auto-generated method stub
				List<Product> products = new ArrayList<>();
				String sql = "SELECT * FROM Products where category_id = ?";
				try(Connection conn = DatabaseConnection.getConnection();
						PreparedStatement sts = conn.prepareStatement(sql);){
					sts.setInt(1, id);
					ResultSet rs = sts.executeQuery();
		        // Process the result set
					while (rs.next()) {
		        	Product product = new Product();
		        	product.setProduct_id(rs.getInt("product_id"));
		        	product.setName(rs.getString("name"));
		        	product.setDescription(rs.getString("description"));
		        	product.setImg_url(rs.getString("image_url"));
		        	product.setPrice(rs.getDouble("price"));
		        	product.setDis_price(rs.getDouble("discount_price"));
		        	product.setQuantity(rs.getInt("quantity_available"));
		        	System.out.println(product.getProduct_id()+" "+product.getName()+" "
		        			+product.getImg_url());
		        	products.add(product);
		        }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				return products;
	}


			public Product getproductdetailsbyId(int product_id) {
				// TODO Auto-generated method stub
				Product product = new Product();
				String sql = "SELECT * FROM Products where product_id = ?";
				try(Connection conn = DatabaseConnection.getConnection();
						PreparedStatement sts = conn.prepareStatement(sql);){
					sts.setInt(1, product_id);
					ResultSet rs = sts.executeQuery();
		        // Process the result set
					
					while (rs.next()) {
					
		        	product.setProduct_id(rs.getInt("product_id"));
		        	product.setSeller_id(rs.getInt("seller_id"));
		        	product.setName(rs.getString("name"));
		        	product.setDescription(rs.getString("description"));
		        	product.setImg_url(rs.getString("image_url"));
		        	product.setPrice(rs.getDouble("price"));
		        	product.setDis_price(rs.getDouble("discount_price"));
		        	product.setQuantity(rs.getInt("quantity_available"));
		        	product.setThres_quanty(rs.getInt("threshold_quantity"));
		        	System.out.println(product.getProduct_id()+" "+product.getName());
//		        	return product;
		        }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				return product;
				
				
				
	}
			public void updateProductbydis_price(int product_id, double dis_price) {
				// TODO Auto-generated method stub
				String sql = "UPDATE products set ? = ? where product_id = ? ";
				try(Connection conn = DatabaseConnection.getConnection();
						PreparedStatement sts = conn.prepareStatement(sql);){
					sts.setDouble(1, dis_price);
					sts.setInt(2, product_id);
		        // Process the result set
					sts.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
			}
			public void updateByquant(int product_id, int quantity) {
				// TODO Auto-generated method stub
				String sql = "UPDATE products set quantity_available= ? where product_id = ? ";
				try(Connection conn = DatabaseConnection.getConnection();
						PreparedStatement sts = conn.prepareStatement(sql);){
					sts.setInt(1, quantity);
					sts.setInt(2, product_id);
		        // Process the result set
					sts.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			public void updateProductbyprice(int product_id, double price) {
				// TODO Auto-generated method stub
				String sql = "UPDATE products set price = ? where product_id = ? ";
				try(Connection conn = DatabaseConnection.getConnection();
						PreparedStatement sts = conn.prepareStatement(sql);){
					sts.setDouble(1, price);
					sts.setInt(2, product_id);
		        // Process the result set
					sts.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			public void removeById(int product_id) {
				// TODO Auto-generated method stub
				String sql = "DELETE from products where product_id = ?";
				try(Connection conn = DatabaseConnection.getConnection();
						PreparedStatement st = conn.prepareStatement(sql)){
					st.setInt(1, product_id);
					st.executeUpdate();
						
				}catch (SQLException e) {
		            e.printStackTrace();
		        }
			}
			public List<Product> getProductsByseller(int seller_id) {
				// TODO Auto-generated method stub
				List<Product> products = new ArrayList<>();
				String sql = "SELECT * FROM Products where seller_id = ?";
				try(Connection conn = DatabaseConnection.getConnection();
						PreparedStatement sts = conn.prepareStatement(sql);){
					sts.setInt(1, seller_id);
					ResultSet rs = sts.executeQuery();
		        // Process the result set
					while (rs.next()) {
		        	Product product = new Product();
		        	product.setProduct_id(rs.getInt("product_id"));
		        	product.setName(rs.getString("name"));
		        	product.setDescription(rs.getString("description"));
		        	product.setImg_url(rs.getString("image_url"));
		        	product.setPrice(rs.getDouble("price"));
		        	product.setDis_price(rs.getDouble("discount_price"));
		        	product.setQuantity(rs.getInt("quantity_available"));
		        	System.out.println(product.getProduct_id()+" "+product.getName()+" "+product.getDescription()
		        			+product.getImg_url()+" "+product.getPrice()+" "+product.getDis_price()+" "+product.getQuantity());
		        	products.add(product);
		        }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				return products;
			}
			public void updateProductbyId(int product_id,Product product) {
				// TODO Auto-generated method stub
				String sql = "UPDATE products set name = ?,description= ?,image_url= ?,price= ?,discount_price= ?,quantity_available= ?,threshold_quantity= ?,category_id = ? where product_id = ? ";
				try(Connection conn = DatabaseConnection.getConnection();
						PreparedStatement st = conn.prepareStatement(sql);){
					
					
					st.setString(1, product.getName());
					st.setString(2, product.getDescription());
					st.setString(3,product.getImg_url());
					st.setDouble(4, product.getPrice());
					st.setDouble(5, product.getDis_price());
					st.setInt(6, product.getQuantity());
					st.setInt(7, product.getThres_quanty());
					st.setInt(8, product.getCategory_id());
					st.setInt(9, product_id);
					
					st.executeUpdate();
		        // Process the result set
					st.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
}

