<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Order" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Placed Orders</title>
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f6f9;
        margin: 0;
        padding: 0;
    }
    .dashboard-container {
        background-color: #ffffff;
        padding: 30px;
        border-radius: 15px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        text-align: center;
        width: 90%;
        max-width: 1200px;
        margin: 0 auto;
    }
    h1 {
        color: #343a40;
        margin-bottom: 30px;
        font-size: 2rem;
        font-weight: 700;
    }
    table {
        width: 100%;
        margin-bottom: 1rem;
        background-color: transparent;
        border-collapse: collapse;
    }
    th, td {
        padding: 12px;
        text-align: left;
        border-top: 1px solid #dee2e6;
    }
    th {
        background-color: #007bff;
        color: white;
    }
</style>
</head>
<body>
    <jsp:include page="NavSeller.jsp" />
    <div class="dashboard-container">
        <h1>Placed Orders</h1>
        <%
            List<Order> orders = (List<Order>) request.getAttribute("order");
            if (orders != null && !orders.isEmpty()) {
        %>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Order ID</th>
                    <th>User ID</th>
                    <th>Total Amount</th>
                    <th>Shipping Address</th>
                    <th>Billing Address</th>
                    <th>Order Status</th>
                    <th>Order Date</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (Order order : orders) {
                %>
                <tr>
                    <td><%= order.getOrder_id() %></td>
                    <td><%= order.getUser_id()%></td>
                    <td>$<%= order.getTotal_amount() %></td>
                    <td><%= order.getShipping_address() %></td>
                    <td><%= order.getBilling_address() %></td>
                    <td><%= order.getOrder_status()%></td>
                    <td><%= order.getOrder_date()%></td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <%
            } else {
        %>
        <div class="alert alert-info">
            No orders placed yet.
        </div>
        <%
            }
        %>
    </div>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
