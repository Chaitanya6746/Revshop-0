<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home Page</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: "Roboto", sans-serif;
            background-color: #f0f0f0;
        }
        .banner-section-bg-container {
            height: 100vh;
            background-image: url("https://t4.ftcdn.net/jpg/05/39/99/67/360_F_539996737_T5gJEIEqsGUjMXhrLZt0lDLcrOWsSHlb.jpg");
            background-size: cover;
            background-position: center;
            display: flex;
            justify-content: center;
            align-items: center;
            text-align: center;
            position: relative;
        }
        .overlay {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: rgba(0, 0, 0, 0.5);
        }
        .content {
            position: relative;
            z-index: 2;
        }
        .banner-heading {
            color: #fff;
            font-size: 45px;
            font-weight: 300;
            margin-bottom: 10px;
        }
        .banner-caption {
            color: #fff;
            font-size: 20px;
            font-weight: 100;
            margin-bottom: 20px;
        }
        .custom-button, .custom-outline-button {
            width: 130px;
            height: 45px;
            color: white;
            font-size: 16px;
            text-transform: uppercase;
            cursor: pointer;
            margin: 5px;
            transition: all 0.3s ease;
        }
        .custom-button {
            background-color: #131AF0;
            border: none;
            border-radius: 8px;
        }
        .custom-button:hover {
            background-color: #0F0EC4;
        }
        .custom-outline-button {
            background-color: transparent;
            border: 2px solid #d0b200;
            border-radius: 8px;
            color: #d0b200;
        }
        .custom-outline-button:hover {
            background-color: #d0b200;
            color: white;
        }
    </style>
    <script>
        function navigateTo(page) {
            window.location.href = page;
        }
    </script>
</head>
<body>
    <div class="banner-section-bg-container">
        <div class="overlay"></div>
        <div class="content">
            <h1 class="banner-heading">Welcome to Chaitanya's RevShop</h1>
            <p class="banner-caption">Buy Smart & Good Products</p>
            <button class="custom-button" onclick="navigateTo('Login.jsp')">Login</button>
            <button class="custom-outline-button" onclick="navigateTo('Registration.jsp')">Register</button>
        </div>
    </div>
</body>
</html>
