<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Category" %> 
<%@ page import="model.Product" %> <!-- Import your Category class -->
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Product</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .container {
            max-width: 800px;
            margin-top: 30px;
        }
        .form-group {
            margin-bottom: 1.5rem;
        }
        .form-control {
            border-radius: 0.25rem;
        }
        .btn-submit {
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 0.25rem;
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        .btn-submit:hover {
            background-color: #45a049;
        }
        .form-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h1 {
            margin-bottom: 20px;
            color: #333;
        }
    </style>
</head>
<body>
	<jsp:include page="navBar.jsp" />
	
    <div class="container">
        <div class="form-container">
            <h1>Update Products to Inventory</h1>
            <form action="ModifyProductServlet" method="POST">
                <div class="form-group">
                <%
                Product product = (Product) request.getAttribute("product");
					%>
					<input type="hidden" name="productId" value="<%= product.getProduct_id() %>">
                    <label for="productName">Product Name:</label>
                    <input type="text" id="productName" name="productName" class="form-control" value="<%= product.getName() %>" required>
                </div>
                <div class="form-group">
                    <label for="productDescription">Product Description:</label>
                    <textarea id="productDescription" name="productDescription" class="form-control" rows="4" required><%= product.getDescription()%></textarea>
                </div>
                <div class="form-group">
                    <label for="productPrice">Product Price:</label>
                    <input type="number" id="productPrice" name="productPrice" value="<%= product.getPrice() %>" class="form-control" step="0.01" required>
                </div>
                <div class="form-group">
                    <label for="discountPrice">Discount Price:</label>
                    <input type="number" id="discountPrice" name="discountPrice" value="<%= product.getDis_price() %>" class="form-control" step="0.01">
                </div>
                <div class="form-group">
                    <label for="quantityAvailable">Quantity Available:</label>
                    <input type="number" id="quantityAvailable" name="quantityAvailable" value="<%= product.getQuantity() %>" class="form-control" required>
                </div>
                <div class="form-group">
                    <label for="thresholdQuantity">Threshold Quantity:</label>
                    <input type="number" id="thresholdQuantity" name="thresholdQuantity" value="<%= product.getThres_quanty() %>" class="form-control">
                </div>
                <div class="form-group">
                    <label for="imageUrl">Image URL:</label>
                    <input type="text" id="imageUrl" name="imageUrl" value="<%= product.getImg_url() %>" class="form-control">
                </div>
                <div class="form-group">
                    <label for="productCategory">Product Category:</label>
                    <select id="productCategory" name="productCategory" class="form-control" required>
                        <!-- Categories will be populated by the servlet -->
                        <%
                            List<Category> categories = (List<Category>) request.getAttribute("categories");
                            if(categories != null && !categories.isEmpty()){
                                for(Category cat : categories){ 
                        %>
                                    <option value="<%= cat.getCategory_id() %>"><%= cat.getCategory_name() %></option>
                        <%
                                }
                            }
                        %>
                    </select>
                </div>
                <div class="form-group">
                    <input type="submit" name="action" value="Update" class="btn-submit">
                </div>
            </form>
        </div>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
