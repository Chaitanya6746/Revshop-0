package service;

import dao.OrderDAO;

public class OrderService {
	private OrderDAO orderDAO = new OrderDAO();

	public void placeOrderfromCart(int user_id,String shipping_add,String billing_add) {
		// TODO Auto-generated method stub
		orderDAO.placeOrderByCart(user_id,shipping_add,billing_add);
		
	}

}
