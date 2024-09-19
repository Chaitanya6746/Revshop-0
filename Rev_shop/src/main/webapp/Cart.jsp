<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Product" %>
<%@ page import="model.Cart" %>
<%@ page import="service.CartService" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Shopping Cart</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
        }
        .container {
            max-width: 900px;
        }
        .card {
            margin-bottom: 1rem;
        }
        .remove-btn {
            background-color: #dc3545;
            color: white;
            border: none;
            padding: 5px 10px;
            border-radius: 5px;
            cursor: pointer;
        }
        .remove-btn:hover {
            background-color: #c82333;
        }
        .order-btn {
            background-color: #28a745;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s ease;
        }
        .order-btn:hover {
            background-color: #218838;
        }
        .table thead th {
            background-color: #343a40;
            color: white;
        }
    </style>
</head>
<body>
    <!-- Navigation Bar -->
<jsp:include page="navBar.jsp" />
    <div class="container mt-4">
        <h1>Your Shopping Cart</h1>

        <!-- Cart Table -->
        <table class="table table-striped">
            <thead>
                <tr>
                    <th scope="col">Product</th>
                    <th scope="col">Price</th>
                    <th scope="col">Quantity</th>
                    <th scope="col">Total</th>
                    <th scope="col">Action</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Product> cartItems = (List<Product>) request.getAttribute("cartitems");
                    double grandTotal = 0.0;
					CartService cartService = new CartService();
                    if (cartItems != null && !cartItems.isEmpty()) {
                        for (Product product : cartItems) {
                            int product_id = product.getProduct_id();// Ensure you have a method to get product by ID
                            System.out.println(product_id);
                            int quantity = cartService.getQuantfromCart(product_id);
                            double totalPrice = product.getDis_price() * quantity;
                            grandTotal += totalPrice;
                %>
                <tr>
                    <td><%= product.getName() %></td>
                    <td>$<%= product.getDis_price()%></td>
                    <td><%= quantity %></td>
                    <td>$<%= String.format("%.2f", totalPrice) %></td>
                    <td>
                        <form action="CartFavServlet" method="post" style="display: inline;">
                            <input type="hidden" name="productId" value="<%= product.getProduct_id() %>">
                            <button type="submit" name="action" value="removeFromCart" class="remove-btn">Remove</button>
                        </form>
                    </td>
                </tr>
                <%
                        }
                %>
                <tr>
                    <td colspan="3" class="text-right"><strong>Grand Total:</strong></td>
                    <td>$<%= String.format("%.2f", grandTotal) %></td>
                    <td></td>
                </tr>
                <%
                    } else {
                %>
                <tr>
                    <td colspan="5" class="text-center">Your cart is empty.</td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>

        <!-- Order Button -->
        <div class="text-right mt-4">
            <form action="addressForm.jsp" method="post">
            <input type="hidden" name="totalAmount" value="<%= String.format("%.2f", grandTotal) %>">
        		<button type="submit" name="action" value="placeOrder" class="order-btn">Place Order</button>
       		</form>
        </div>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
