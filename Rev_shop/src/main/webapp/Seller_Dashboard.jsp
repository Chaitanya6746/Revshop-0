<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Product" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Seller Dashboard</title>
<!-- Include Bootstrap CSS -->
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
    .button-container {
        margin-bottom: 30px;
        display:flex;
    }
    .button {
        background-color: #007bff;
        color: white;
        padding: 12px 24px;
        margin: 8px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        font-size: 16px;
        transition: background-color 0.3s ease;
        text-decoration: none;
        display: inline-block;
    }
    .button:hover {
        background-color: #0056b3;
    }
    .button.modify {
        background-color: #28a745;
    }
    .button.modify:hover {
        background-color: #218838;
    }
    .button.delete {
        background-color: #dc3545;
    }
    .button.delete:hover {
        background-color: #c82333;
    }
    .product-card {
        border: 1px solid #dee2e6;
        border-radius: 10px;
        overflow: hidden;
        margin-bottom: 20px;
        transition: transform 0.3s, box-shadow 0.3s;
        background-color: #ffffff;
    }
    .product-card:hover {
        transform: translateY(-5px);
        box-shadow: 0 8px 16px rgba(0, 0, 0, 0.15);
    }
    .product-image {
        height: 200px;
        object-fit: cover;
        width: 100%;
    }
    .product-details {
        padding: 15px;
    }
    .product-price {
        color: #28a745;
        font-weight: bold;
        font-size: 1.25rem;
    }
    .discount-price {
        color: #dc3545;
        font-weight: bold;
        text-decoration: line-through;
        margin-right: 0.5rem;
    }
    .no-products {
        padding: 15px;
        font-size: 1.2rem;
        color: #6c757d;
        text-align: center;
    }
    .alert-info {
        font-size: 1.2rem;
        padding: 15px;
    }
    .button-group {
        margin-top: 10px;
        display:flex;
    }
    .button-group button {
        margin: 5px;
    }
</style>
</head>
<body>
    <!-- Navigation Bar -->
    <jsp:include page="NavSeller.jsp" />

    <div class="dashboard-container">
        <h1>Seller Dashboard</h1>
        <div class="button-container">
            <form action="SellerOpsServlet" method="post">
                <button type="submit" name="action" value="Add Products" class="button">Add Products to Inventory</button>
            </form>
            <form action="SellerOpsServlet" method="get">
                <button type="submit" name="action" value="See Placed Orders" class="button">See Placed Orders</button>
            </form>
        </div>

        <hr style="margin: 30px 0;">

        <!-- Product Display Section -->
        <h2>Available Products</h2>
        <div class="container">
            <div class="row">
                <!-- Assume `products` is a List<Product> passed from the servlet -->
                <%
                    List<Product> products = (List<Product>) request.getAttribute("products");
                    if (products != null && !products.isEmpty()) {
                        for (Product product : products) {
                %>
                    <div class="col-md-4 col-sm-6">
                        <div class="card product-card">
                            <img src="<%= product.getImg_url() %>" class="card-img-top product-image" alt="<%= product.getName() %>">
                            <div class="card-body product-details">
                                <h5 class="card-title"><%= product.getName() %></h5>
                                <p class="card-text"><%= product.getDescription() %></p>
                                <div>
                                    <% if (product.getDis_price() > 0) { %>
                                        <span class="discount-price">$<%= product.getPrice() %></span>
                                    <% } %>
                                    <span class="product-price">$<%=product.getDis_price() > 0 ? product.getDis_price() : product.getPrice() %></span>
                                </div>
                                <p class="card-text"><strong>Available Quantity:</strong> <%= product.getQuantity() %></p>
                                <div class="button-group">
                                    <form action="ModifyProductServlet" method="POST" style="display: inline;">
                                        <input type="hidden" name="productId" value="<%= product.getProduct_id() %>">
                                        <button type="submit" name="action" value="Modify" class="button modify">Modify</button>
                                    </form>
                                    <form action="ModifyProductServlet" method="post" style="display: inline;">
                                        <input type="hidden" name="productId" value="<%= product.getProduct_id() %>">
                                        <button type="submit" name="action" value="Delete" class="button delete">Delete</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                <%
                        }
                    } else {
                %>
                    <div class="col-12">
                        <div class="no-products">
                            No products available at the moment.
                        </div>
                    </div>
                <%
                    }
                %>
            </div>
        </div>
    </div>

    <!-- Include Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
