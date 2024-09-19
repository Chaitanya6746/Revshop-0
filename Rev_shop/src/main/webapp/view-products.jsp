<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Product" %>
<%@ page import="model.Category" %>
<%@ page import="model.Review" %>
<%@ page import="service.ReviewService" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Products</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .dropdown-menu a {
            color: #000 !important;
        }
        .product-card {
            border: 1px solid #ddd;
            border-radius: 0.5rem;
            overflow: hidden;
            margin-bottom: 1.5rem;
            transition: transform 0.2s;
        }
        .product-card:hover {
            transform: scale(1.05);
        }
        .product-image {
            height: 200px;
            object-fit: cover;
        }
        .product-details {
            padding: 15px;
        }
        .product-price {
            color: #28a745;
            font-weight: bold;
        }
        .discount-price {
            color: #dc3545;
            font-weight: bold;
            text-decoration: line-through;
            margin-right: 0.5rem;
        }
        .button-group {
            display: flex;
            align-items: center;
            margin-top: 10px;
        }
        .button-group button {
            margin: 5px;
        }
        .button-group input{
        	width:50px;
        } 
        .button-container {
            margin-bottom: 30px;
        }
        .button {
            background-color: #28a745;
            color: white;
            padding: 8px 8px;
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
            background-color: #218838;
        }
        .button.modify {
            background-color: #393ABA;
        }
        .button.modify:hover {
            background-color: #0056b3;
        }
        .button.delete {
            background-color: #469C5E;
        }
        .button.delete:hover {
            background-color: #c82333;
        }
        /* Custom styling for the dropdown */
        .form-control-custom {
            border: 1px solid #ced4da;
            border-radius: 0.375rem;
            padding: 0.375rem 0.75rem;
            background-color: #fff;
            font-size: 1rem;
            line-height: 1.5;
            color: #495057;
            width: 100%;
        }
        .form-control-custom:focus {
            border-color: #28a745;
            box-shadow: 0 0 0 0.2rem rgba(40, 167, 69, 0.25);
        }
        .filter-button {
            height: 100%;
            padding: 0.375rem 0.75rem;
        }
        .quantity-input {
            width: 70px;
        }
    </style>
</head>
<body>
    <!-- Navigation Bar -->
    <jsp:include page="navBar.jsp" />

    <div class="container mt-4">
        <h1 class="mb-4">Available Products</h1>

        <!-- Dropdown for categories and filter button -->
        <form action="CartFavServlet" method="post">
            <div class="form-row align-items-center mb-4">
                <div class="col-auto">
                    <label class="sr-only" for="productCategory">Product Category</label>
                    <select id="productCategory" name="productcat" class="form-control form-control-custom" required>
                        <!-- Categories will be populated by the servlet -->
                        <%
                            List<Category> categories = (List<Category>) request.getAttribute("categories");
                            if (categories != null && !categories.isEmpty()) {
                                for (Category cat : categories) {
                        %>
                            <option value="<%= cat.getCategory_id() %>"><%= cat.getCategory_name() %></option>
                        <%
                                }
                            }
                        %>
                    </select>
                </div>
                <div class="col-auto">
                    <button type="submit" name="action" value="selectCat" class="button modify filter-button">Filter</button>
                </div>
            </div>
        </form>

        <div class="row">
            <!-- Assume `products` is a List<Product> passed from the servlet -->
            <%
                List<Product> products = (List<Product>) request.getAttribute("products");
            	
                if (products != null && !products.isEmpty()) {
                    for (Product product : products) {
                    	
            %>
                <div class="col-md-4">
                    <div class="card product-card">
                        <img src="<%= product.getImg_url() %>" class="card-img-top product-image" alt="<%= product.getName() %>">
                        <div class="card-body product-details">
                            <h5 class="card-title"><%= product.getName() %></h5>
                            <p class="card-text"><%= product.getDescription() %></p>
                            <div>
                                <% if (product.getDis_price() > 0) { %>
                                    <span class="discount-price">$<%= product.getPrice() %></span>
                                <% } %>
                                <span class="product-price">$<%= product.getDis_price() > 0 ? product.getDis_price() : product.getPrice() %></span>
                            </div>
                            <p class="card-text"><strong>Available Quantity:</strong> <%= product.getQuantity() %></p>
                            <form action="CartFavServlet" method="POST">
                                <input type="hidden" name="productId" value="<%= product.getProduct_id() %>">
                                <div class="button-group">
                                    <input type="number" name="quantity" class="form-control quantity-input" min="1" max="<%= product.getQuantity() %>" value="1">
                                    <button type="submit" name="action" value="addtoCart" class="button modify">Add to Cart</button>
                                    <button type="submit" name="action" value="addtoFavs" class="button delete">Add to Favorites</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            <%
                    }
                } else {
            %>
                <div class="col-12">
                    <div class="alert alert-info" role="alert">
                        No products available at the moment.
                    </div>
                </div>
            <%
                }
            %>
        </div>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
