<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Buyer Dashboard</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <link href="styles.css" rel="stylesheet"> <!-- Link to external CSS file -->
    <style>
        /* General Body Styles */
        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            background-color: #f4f7f9;
            color: #333;
        }

        /* Container Styles */
        .container {
            max-width: 900px;
        }

        /* Navbar Styles */
        .navbar {
            background-color: #343a40;
        }

        .navbar-brand, .nav-link {
            color: #fff !important;
        }

        /* Card Styles */
        .card {
            border: none;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            margin-bottom: 1.5rem;
            transition: box-shadow 0.3s ease, transform 0.3s ease;
        }

        .card:hover {
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
            transform: translateY(-5px);
        }

        .card-header {
            border-bottom: 0;
            border-radius: 10px 10px 0 0;
            padding: 1rem;
        }

        .card-body {
            padding: 1.5rem;
        }

        .card-body a {
            display: block;
            color: #007bff;
            text-decoration: none;
            transition: color 0.3s ease;
        }

        .card-body a:hover {
            color: #0056b3;
            text-decoration: underline;
        }

        /* Button Styles */
        button {
            background-color: #28a745;
            border: none;
            color: white !important;
            padding: 0.75rem 1.5rem;
            border-radius: 5px;
            font-size: 1rem;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #218838;
        }

        /* Header Styles */
        h1, h2 {
            color: #212529;
        }

        h1 {
            font-size: 2.5rem;
            margin-bottom: 1.5rem;
        }

        h2 {
            font-size: 2rem;
            margin-bottom: 1rem;
        }
    </style>
</head>
<body>
    <!-- Navigation Bar (Included from navBar.jsp) -->
<jsp:include page="navBar.jsp" />
    <div class="container">
        <h1 class="mt-4">Buyer Dashboard</h1>

        <!-- Main content area -->
        <div class="mt-4">
            <%
                User user = (User) request.getAttribute("User");
                if (user != null) {
            %>
                <h2>Welcome to Your Buyer Dashboard, <%=user.getUsername() %>!</h2>
            <%
                }
            %>
        </div>

        <!-- List of Operations -->
        <div class="mt-4">
            <div class="row">
                <div class="col-md-4">
                    <div class="card">
                        <div class="card-header bg-primary text-white">
                            View Products
                        </div>
                        <div class="card-body">
                            <a href="view-products">View All Products</a>
                        </div>
                    </div>
                </div>

                <div class="col-md-4">
                    <form action="CartFavServlet">
                        <div class="card">
                            <div class="card-header bg-success text-white">
                                Manage Cart
                            </div>
                            <div class="card-body">
                                <button type="submit" name="action" value="ManageCart">Show Cart</button>
                            </div>
                        </div>
                    </form>
                </div>

                <div class="col-md-4">
                    <div class="card">
                        <div class="card-header bg-warning text-dark">
                            Account Actions
                        </div>
                        <div class="card-body">
                            <a href="profile.jsp">View Profile</a>
                            <a href="update-profile.jsp">Update Profile</a>
                            <a href="Login.jsp">Logout</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
