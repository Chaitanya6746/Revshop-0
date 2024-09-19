package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Cart;
import model.Order;
import model.OrderItems;
import model.Product;

public class OrderDAO {	
	private ProductDAO productDAO = new ProductDAO();
	private CartDAO cartDAO = new CartDAO();
    public int placeOrderByCart(int user_id, String shipping_add, String billing_add) {
    	 String insertOrderQuery = "INSERT INTO Orders (user_id, total_amount, shipping_address, billing_address) VALUES (?, ?, ?, ?)";
         String insertOrderItemQuery = "INSERT INTO Order_Items (order_id, product_id, quantity, price_at_purchase) VALUES (?, ?, ?, ?)";
         String selectProductQuery = "SELECT price FROM Products WHERE product_id = ?";
         String deleteCartQuery = "DELETE FROM Cart WHERE user_id = ?";

         double total_amount = 0;
         try (Connection conn = DatabaseConnection.getConnection()) {
             // Get product quantities from the cart
             Map<Integer, Integer> productQuantities = getQuantitiesByUserId(user_id);

             // Calculate total amount
             for (Map.Entry<Integer, Integer> entry : productQuantities.entrySet()) {
                 int product_id = entry.getKey();
                 int quantity = entry.getValue();
                 double price = 0;

                 try (PreparedStatement selectProductStmt = conn.prepareStatement(selectProductQuery)) {
                     selectProductStmt.setInt(1, product_id);
                     ResultSet productRs = selectProductStmt.executeQuery();
                     if (productRs.next()) {
                         price = productRs.getDouble("price");
                     }
                 }

                 double priceAtPurchase = price;
                 total_amount += priceAtPurchase * quantity;
             }

             // Insert order and get generated order_id
             int orderId = -1;
             try (PreparedStatement insertOrderStmt = conn.prepareStatement(insertOrderQuery, PreparedStatement.RETURN_GENERATED_KEYS)) {
                 insertOrderStmt.setInt(1, user_id);
                 insertOrderStmt.setDouble(2, total_amount);
                 insertOrderStmt.setString(3, shipping_add);
                 insertOrderStmt.setString(4, billing_add);
                 insertOrderStmt.executeUpdate();

                 ResultSet generatedKeys = insertOrderStmt.getGeneratedKeys();
                 if (generatedKeys.next()) {
                     orderId = generatedKeys.getInt(1);
                 }
             }

             // Insert items into Order_Items table
             if (orderId != -1) {
                 try (PreparedStatement insertOrderItemStmt = conn.prepareStatement(insertOrderItemQuery)) {
                     for (Map.Entry<Integer, Integer> entry : productQuantities.entrySet()) {
                         int product_id = entry.getKey();
                         int quantity = entry.getValue();
                         double price = 0;

                         try (PreparedStatement selectProductStmt = conn.prepareStatement(selectProductQuery)) {
                             selectProductStmt.setInt(1, product_id);
                             ResultSet productRs = selectProductStmt.executeQuery();
                             if (productRs.next()) {
                                 price = productRs.getDouble("price");
                             }
                         }

                         double priceAtPurchase = price;

                         insertOrderItemStmt.setInt(1, orderId);
                         insertOrderItemStmt.setInt(2, product_id);
                         insertOrderItemStmt.setInt(3, quantity);
                         insertOrderItemStmt.setDouble(4, priceAtPurchase);
                         insertOrderItemStmt.executeUpdate();
                     }
                 }
             }

             // Clear the cart
             try (PreparedStatement deleteCartStmt = conn.prepareStatement(deleteCartQuery)) {
                 deleteCartStmt.setInt(1, user_id);
                 deleteCartStmt.executeUpdate();
             }
             return orderId;

         } catch (SQLException e) {
             e.printStackTrace();
         }
         return -1;
     }

	public Map<Integer, Integer> getQuantitiesByUserId(int user_id) {
		// TODO Auto-generated method stub
		String selectCartQuery = "SELECT product_id, quantity FROM Cart WHERE user_id = ?";
        Map<Integer, Integer> productQuantities = new HashMap<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement selectCartStmt = conn.prepareStatement(selectCartQuery)) {
            selectCartStmt.setInt(1, user_id);
            ResultSet rs = selectCartStmt.executeQuery();

            while (rs.next()) {
                int productId = rs.getInt("product_id");
                int quantity = rs.getInt("quantity");
                productQuantities.put(productId, quantity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productQuantities;
    }


	public Order getOrder(int order_id) {
		// TODO Auto-generated method stub
		
		String sql = "Select * from Orders where order_id=?";
		try (Connection conn = DatabaseConnection.getConnection();
	             PreparedStatement selectCartStmt = conn.prepareStatement(sql)) {
	            selectCartStmt.setInt(1, order_id);
	            ResultSet rs = selectCartStmt.executeQuery();
	            
	            while (rs.next()) {
	            	Order order = new Order();
	            	order.setTotal_amount(rs.getDouble("total_amount"));
	            	order.setShipping_address(rs.getString("shipping_address"));
	                order.setBilling_address(rs.getString("billing_address"));
	                order.setOrder_status(rs.getString("order_status"));
	                return order;
	            }
		}catch (SQLException e) {
	                e.printStackTrace();
	            }
		return null;
}

	public List<Product> getAllItems(int orderId) {
		// TODO Auto-generated method stub
		List<Product> products = new ArrayList<>();
		
		String sql = "Select * from Order_Items where order_id = ?";
		try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement selectCartStmt = conn.prepareStatement(sql)) {
       selectCartStmt.setInt(1, orderId);
       ResultSet rs = selectCartStmt.executeQuery();
       
       while (rs.next()) {
    	   OrderItems orderItems = new OrderItems();
       		orderItems.setPrice_at_purchase(rs.getDouble("price_at_purchase"));
       		orderItems.setQuantity(rs.getInt("quantity"));
       		orderItems.setProduct_id(rs.getInt("product_id"));
       		orderItems.setOrder_id(rs.getInt("order_id"));
       		Product product = productDAO.getproductdetailsbyId(rs.getInt("product_id"));
       		products.add(product);
       }
	}catch (SQLException e) {
	           e.printStackTrace();
	       }
	return products;
		}

	public List<Order> getHistoryuserId(int userId) {
		// TODO Auto-generated method stub
		List<Order> orders = new ArrayList<>();
		
		String sql = "Select * from Orders where user_id = ?";
		 try (Connection conn = DatabaseConnection.getConnection();
	             PreparedStatement ps = conn.prepareStatement(sql)) {
	            ps.setInt(1, userId);
	            ResultSet rs = ps.executeQuery();

	            while (rs.next()) {
	                Order order = new Order();
	                order.setOrder_id(rs.getInt("order_id"));
	                order.setUser_id(rs.getInt("user_id"));
	                order.setTotal_amount(rs.getDouble("total_amount"));
	                order.setShipping_address(rs.getString("shipping_address"));
	                order.setBilling_address(rs.getString("billing_address"));
	                order.setOrder_status(rs.getString("order_status"));
	                order.setOrder_date(rs.getTimestamp("order_date"));
	                orders.add(order);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		return orders;
	}

	public List<Order> getOrders() {
		// TODO Auto-generated method stub
List<Order> orders = new ArrayList<>();
		
		String sql = "Select * from Orders";
		 try (Connection conn = DatabaseConnection.getConnection();
	             PreparedStatement ps = conn.prepareStatement(sql)) {
	            ResultSet rs = ps.executeQuery();

	            while (rs.next()) {
	                Order order = new Order();
	                order.setOrder_id(rs.getInt("order_id"));
	                order.setUser_id(rs.getInt("user_id"));
	                order.setTotal_amount(rs.getDouble("total_amount"));
	                order.setShipping_address(rs.getString("shipping_address"));
	                order.setBilling_address(rs.getString("billing_address"));
	                order.setOrder_status(rs.getString("order_status"));
	                order.setOrder_date(rs.getTimestamp("order_date"));
	                orders.add(order);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
		return orders;
	}
}
