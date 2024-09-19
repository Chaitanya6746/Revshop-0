<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="model.Product" %>
<%@ page import="service.FavService" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Favorites</title>
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
        .button {
            background-color: #28a745;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s ease;
        }
        .button:hover {
            background-color: #218838;
        }
        .product-image {
            max-height: 150px;
            object-fit: cover;
        }
        .product-title {
            font-size: 1.25rem;
        }
    </style>
</head>
<body>
    <!-- Navigation Bar -->
    <jsp:include page="navBar.jsp" />

    <div class="container mt-4">
        <h1>Your Favorites</h1>

        <!-- Favorites Table -->
        <table class="table table-striped">
            <thead>
                <tr>
                    <th scope="col">Image</th>
                    <th scope="col">Product</th>
                    <th scope="col">Price</th>
                    <th scope="col">Action</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<Product> favItems = (List<Product>) request.getAttribute("favitems");
                    if (favItems != null && !favItems.isEmpty()) {
                        for (Product product : favItems) {
                %>
                <tr>
                    <td><img src="<%= product.getImg_url() %>" alt="<%= product.getName() %>" class="img-fluid product-image"></td>
                    <td><%= product.getName() %></td>
                    <td>$<%= product.getDis_price() > 0 ? product.getDis_price() : product.getPrice() %></td>
                    <td>
                        <form action="CartFavServlet" method="post" style="display: inline;">
                            <input type="hidden" name="productId" value="<%= product.getProduct_id() %>">
                            <button type="submit" name="action" value="removeFromFavs" class="remove-btn">Remove</button>
                        </form>
                        <button type="button" class="button" data-toggle="modal" data-target="#quantityModal" data-product-id="<%= product.getProduct_id() %>" data-product-name="<%= product.getName() %>">
                            Add to Cart
                        </button>
                    </td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="4" class="text-center">No favorite items found.</td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>

        <!-- Back to Products Button -->
        	<div class="text-right mt-4">
            <a href="view-products" class="button">Back to Products</a>
        	</div>

    </div>

    <!-- Quantity Modal -->
    <div class="modal fade" id="quantityModal" tabindex="-1" role="dialog" aria-labelledby="quantityModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="quantityModalLabel">Add to Cart</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="addToCartForm" action="CartFavServlet" method="post">
                        <input type="hidden" name="productId" id="modalProductId">
                        <div class="form-group">
                            <label for="quantity">Quantity</label>
                            <input type="number" class="form-control" id="modalQuantity" name="quantity" min="1" required>
                        </div>
                        <button type="submit" name="action" value="addtoCart" class="button">Add to Cart</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script>
        // Script to populate the modal with product details
        $('#quantityModal').on('show.bs.modal', function (event) {
            var button = $(event.relatedTarget); // Button that triggered the modal
            var productId = button.data('product-id'); // Extract info from data-* attributes
            var productName = button.data('product-name');
            
            var modal = $(this);
            modal.find('#modalProductId').val(productId);
            modal.find('#quantityModalLabel').text('Add ' + productName + ' to Cart');
        });
    </script>
</body>
</html>
