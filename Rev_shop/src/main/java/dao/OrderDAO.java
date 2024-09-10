package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderDAO {
	private OrderItemsDAO orderItemsDAO = new OrderItemsDAO();

	public void placeOrderByCart(int user_id,String shipping_add,String billing_add) {
			// TODO Auto-generated method stub
			String selectCartQuery = "SELECT * from Cart where user_id = ?";
			String insertOrderQuery = "INSERT INTO Orders (user_id, total_amount, shipping_address, billing_address) VALUES (?, ?, ?, ?)";
			String insertOrderItemQuery = "INSERT INTO Order_Items (order_id, product_id, quantity, price_at_purchase) VALUES (?, ?, ?, ?)";
		    String selectProductQuery = "SELECT price FROM Products WHERE product_id = ?";
		    double total_amount = 0;
			try(Connection conn = DatabaseConnection.getConnection();
					PreparedStatement st = conn.prepareStatement(selectCartQuery)){
				st.setInt(1, user_id);
				ResultSet rs = st.executeQuery();
		        // Process the result set
					while (rs.next()) {
						int product_id = rs.getInt("product_id");
		                int quantity = rs.getInt("quantity");
		                double price = 0;
		                try (PreparedStatement ps = conn.prepareStatement(selectProductQuery)) {
		                    ps.setInt(1, product_id);
		                    ResultSet productRs = ps.executeQuery();
		                    if (productRs.next()) {
		                        price = productRs.getDouble("price");
		                    }
		                }

		                double priceAtPurchase = price;
		                total_amount += priceAtPurchase * quantity;
		                orderDAO.addToOrderItems(order_id,product_id,quantity,priceAtPurchase);
		                OrderItem orderItem = new OrderItem(product_id, quantity, priceAtPurchase);
		                orderItems.add(orderItem);
		            }
		        }
		        }
					
			}
			catch (SQLException e) {
	            e.printStackTrace();
	        }
			
	}
	
}
