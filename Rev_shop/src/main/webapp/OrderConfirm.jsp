<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="model.Order" %>
<%@ page import="model.Product" %>
<%@ page import="service.OrderService" %>
<%@ page import="service.CartService" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order Confirmation</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
        }
        .container {
            max-width: 900px;
        }
        .confirmation-card {
            margin-top: 2rem;
            padding: 20px;
            border: 1px solid #e0e0e0;
            border-radius: 8px;
            background-color: #f9f9f9;
        }
        .confirmation-card h2 {
            margin-bottom: 1rem;
        }
        .confirmation-card .order-details {
            margin-bottom: 1rem;
        }
        .confirmation-card .order-details h4 {
            margin-bottom: 0.5rem;
        }
        .order-item {
            border-bottom: 1px solid #e0e0e0;
            padding-bottom: 10px;
            margin-bottom: 10px;
        }
        .order-item:last-child {
            border-bottom: none;
        }
    </style>
</head>
<body>
    <!-- Navigation Bar -->
    <jsp:include page="navBar.jsp" />
    <div class="container">
        <div class="confirmation-card">
            <h2>Thank you for your order!</h2>
            <p>Your order has been placed successfully. Below are the details of your purchase:</p>
            
            <div class="order-details">
                <h4>Order Summary</h4>
                <%
                    int orderId = (int) request.getAttribute("orderId");
                    Map<Integer, Integer> productQuantities = (Map<Integer, Integer>) request.getAttribute("productQuantities");
                    OrderService orderService = new OrderService();
                    Order order = orderService.getOrderById(orderId);
                    List<Product> orderedItems = orderService.getOrderItems(orderId);
                    double grandTotal = order.getTotal_amount();
                    for (Product product : orderedItems) {
                        int quantity = productQuantities.get(product.getProduct_id());
                        double totalPrice = product.getDis_price()* quantity;
                %>
                <div class="order-item">
                    <p><strong>Product:</strong> <%= product.getName() %></p>
                    <p><strong>Price:</strong> $<%= product.getDis_price() %></p>
                    <p><strong>Quantity:</strong> <%= quantity %></p>
                    <p><strong>Total:</strong> $<%= String.format("%.2f", totalPrice) %></p>
                </div>
                <%
                    }
                %>
                <div class="order-total">
                    <p><strong>Grand Total: $<%= String.format("%.2f", grandTotal) %></strong></p>
                </div>
            </div>
            
            <div class="customer-info">
                <h4>Shipping Information</h4>
                <p><strong>Address:</strong> <%= order.getShipping_address() %></p>
            </div>
            
            <p>If you have any questions about your order, please contact our support team.</p>
            <div class="btn-group">
            	<form action="view-products" method="post">
                <button type="submit" class="btn btn-primary">Continue Shopping</button>
	            </form>
	            
            </div>
        </div>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
