<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="navBar.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Enter Shipping and Billing Information</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body {
            
        }
        .container {
            max-width: 600px;
        }
        .form-group {
            margin-bottom: 1rem;
        }
        .submit-btn {
            background-color: #28a745;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s ease;
        }
        .submit-btn:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>
    <!-- Navigation Bar -->
   	

    <div class="container mt-4">
        <h1>Enter Shipping and Billing Information</h1>
        <form action="OrderServlet" method="post">
            <div class="form-group">
                <label for="shippingAddress">Shipping Address:</label>
                <textarea class="form-control" id="shippingAddress" name="shippingAddress" rows="3" required></textarea>
            </div>
            <div class="form-group">
                <label for="billingAddress">Billing Address:</label>
                <textarea class="form-control" id="billingAddress" name="billingAddress" rows="3" required></textarea>
            </div>
            <input type="hidden" name="action" value="placeOrder">
            <button type="submit" class="submit-btn">Submit</button>
        </form>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
