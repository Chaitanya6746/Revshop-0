<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Product" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Review Products</title>
    <style>
        /* Include the same styles from the previous example */
        body {
            font-family: Arial, sans-serif;
            background: #f4f4f9;
            margin: 0;
            padding: 0;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            background: white;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            width: 80%;
            max-width: 800px;
            padding: 20px;
            box-sizing: border-box;
        }
        h1 {
            color: #333;
            text-align: center;
            margin-bottom: 20px;
        }
        .product-form {
            margin-bottom: 20px;
            padding: 15px;
            border: 1px solid #ddd;
            border-radius: 4px;
            background: #fafafa;
        }
        label {
            font-weight: bold;
            display: block;
            margin: 10px 0 5px;
        }
        select, textarea {
            width: 100%;
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
            margin-bottom: 20px;
            font-size: 16px;
        }
        textarea {
            resize: vertical;
        }
        .submit-btn {
            display: block;
            width: 100%;
            padding: 10px;
            background-color: #5cb85c;
            border: none;
            color: white;
            font-size: 16px;
            border-radius: 4px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        .submit-btn:hover {
            background-color: #4cae4c;
        }
    </style>
</head>
<body>
<jsp:include page="navBar.jsp" />
    <div class="container">
        <h1>Review Your Products</h1>
        <form action="review" method="post">
            <% 
                List<Product> products = (List<Product>) request.getAttribute("products");
                for (Product product : products) {
            %>
                <div class="product-form">
                    <h2><%= product.getName() %></h2>
                    <input type="hidden" name="productIds" value="<%= product.getProduct_id() %>">
                    <label for="rating_<%= product.getProduct_id() %>">Rating:</label>
                    <select name="ratings" id="rating_<%= product.getProduct_id() %>">
                        <option value="1">1 - Poor</option>
                        <option value="2">2 - Fair</option>
                        <option value="3">3 - Good</option>
                        <option value="4">4 - Very Good</option>
                        <option value="5">5 - Excellent</option>
                    </select><br/>
                    <label for="comment_<%= product.getProduct_id() %>">Comment:</label><br />
                    <textarea name="comments" id="comment_<%= product.getProduct_id() %>" rows="4" cols="50" placeholder="Write your review here..."></textarea><br />
                </div>
            <% 
                }
            %>
            <input type="submit" value="Submit Reviews" class="submit-btn" />
        </form>
    </div>
</body>
</html>
