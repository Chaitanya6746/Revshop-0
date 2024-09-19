<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Order" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Order History</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
           
        }
        .container {
            max-width: 900px;
        }
        .order-card {
            margin-top: 2rem;
            padding: 20px;
            border: 1px solid #e0e0e0;
            border-radius: 8px;
            background-color: #f9f9f9;
        }
        .order-card h2 {
            margin-bottom: 1rem;
        }
        .order-details {
            margin-bottom: 1rem;
        }
        .order-details h4 {
            margin-bottom: 0.5rem;
        }
    </style>
</head>
<body>
<jsp:include page="navBar.jsp" />
    <div class="container">
        <h2>Your Order History</h2>
        <%
            List<Order> orderHistory = (List<Order>) request.getAttribute("orderHistory");
            if (orderHistory != null && !orderHistory.isEmpty()) {
                for (Order order : orderHistory) {
        %>
        <div class="order-card">
            <h4>Order ID: <%= order.getOrder_id() %></h4>
            <p><strong>Total Amount:</strong> $<%= String.format("%.2f", order.getTotal_amount()) %></p>
            <p><strong>Shipping Address:</strong> <%= order.getShipping_address() %></p>
            <p><strong>Billing Address:</strong> <%= order.getBilling_address() %></p>
            <p><strong>Order Status:</strong> <%= order.getOrder_status() %></p>
            <p><strong>Order Date:</strong> <%= order.getOrder_date() %></p>
        </div>
        <form action="review" method="get">
	            	<input type="hidden" name="orderId" value="<%=order.getOrder_id() %>">
	                <button type="submit" class="btn btn-warning">Review Products</button>
	    </form>
        <% 
                }
                
            } else { 
        %>
        <p>You have no orders.</p>
        <% } %>
        
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
