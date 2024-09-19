<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page import="jakarta.servlet.http.HttpServletRequest" %>
<%@ page import="jakarta.servlet.http.HttpServletResponse" %>
<%@ page import="model.User" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Navbar</title>
    <!-- Bootstrap CSS -->
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .navbar {
            background-color: #343a40;
        }
        .navbar-brand, .nav-link {
            color: #fff !important;
        }
        .navbar-nav .nav-link {
            padding: 0.5rem 1rem;
        }
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
        .dropdown-menu {
            background-color: #343a40;
        }
        .dropdown-item {
            color: #fff !important;
        }
        .dropdown-item:hover {
            background-color: #495057;
        }
    </style>
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="#">Rev Shop</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="Home.jsp">Home</a>
                </li>
                <li class="nav-item">
                    <form action="AddProductServlet" method="get" class="form-inline">
                        <button class="btn btn-manage" name="action" value="showdash" type="submit">Dashboard</button>
                    </form>
                </li>
                
                <li class="nav-item">
                    <form action="" method="post" class="form-inline">
                        <button class="btn btn-manage" name="action" value="ManageCart" type="submit">Manage Products</button>
                    </form>
                </li>
                <li class="nav-item">
                    <form action="SellerOpsServlet" method="get" class="form-inline">
                        <button class="btn btn-manage" name="action" value="See Placed Orders" type="submit">View Orders</button>
                    </form>
                </li>
                <li class="nav-item">
                    <form action="review" method="get" class="form-inline">
                        <button class="btn btn-manage" name="action" value="showreview" type="submit">View Reviews</button>
                    </form>
                </li>
                
                <li class="nav-item">
                    <form action="SellerOpsServlet" method="post" class="form-inline">
                        <button class="btn btn-danger" name="action" value="Logout" type="submit">Logout</button>
                    </form>
                </li>
                
            </ul>
        </div>
    </nav>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
