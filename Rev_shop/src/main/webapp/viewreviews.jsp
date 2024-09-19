<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Review" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product Reviews</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 80%;
            margin: auto;
            overflow: hidden;
            padding: 20px;
            background: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin-top: 30px;
            border-radius: 8px;
        }
        h1 {
            text-align: center;
            color: #333;
            margin-bottom: 20px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        table, th, td {
            border: 1px solid #ddd;
        }
        th, td {
            padding: 15px;
            text-align: left;
        }
        th {
            background-color: #f4f4f4;
        }
        tbody tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        tbody tr:hover {
            background-color: #f1f1f1;
        }
        .no-reviews {
            text-align: center;
            font-size: 18px;
            color: #666;
        }
    </style>
</head>
<body>
    <jsp:include page="NavSeller.jsp" />
    <div class="container">
        <h1>Product Reviews</h1>
        <%
            List<Review> viewreviews = (List<Review>) request.getAttribute("reviews");
            if (viewreviews != null && !viewreviews.isEmpty()) {
        %>
        <table>
            <thead>
                <tr>
                    <th>Review ID</th>
                    <th>User ID</th>
                    <th>Product ID</th>
                    <th>Rating</th>
                    <th>Comment</th>
                    <th>Review Date</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (Review review : viewreviews) {
                %>
                <tr>
                    <td><%= review.getReviewId() %></td>
                    <td><%= review.getUserId() %></td>
                    <td><%= review.getProductId() %></td>
                    <td><%= review.getRating() %></td>
                    <td><%= review.getComment() %></td>
                    <td><%= review.getReviewDate() %></td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <%
            } else {
        %>
        <p class="no-reviews">No reviews available.</p>
        <%
            }
        %>
    </div>
</body>
</html>
