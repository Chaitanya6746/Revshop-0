package service;

import java.util.List;
import java.util.Map;

import dao.OrderDAO;
import model.Order;
import model.Product;

public class OrderService {
	private OrderDAO orderDAO = new OrderDAO();

	public int  placeOrderfromCart(int user_id,String shipping_add,String billing_add) {
		// TODO Auto-generated method stub
		return orderDAO.placeOrderByCart(user_id,shipping_add,billing_add);
		
	}
	public Order getOrderById(int order_id){
		return orderDAO.getOrder(order_id);
		
	}
	public List<Product> getOrderItems(int orderId){
		return orderDAO.getAllItems(orderId);
	}
	public List<Order> getOrderHistoryByUserId(int userId) {
		// TODO Auto-generated method stub
		
		return orderDAO.getHistoryuserId(userId);
	}
	public Map<Integer, Integer> getQuantitiesByUserId(int userId) {
		// TODO Auto-generated method stub
		return orderDAO.getQuantitiesByUserId(userId);
	}
	public List<Order> getALlOrder(){
		return orderDAO.getOrders();
		
	}

}
