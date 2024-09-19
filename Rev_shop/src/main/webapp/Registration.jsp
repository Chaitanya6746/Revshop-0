<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Registration Form</title>
<!-- Bootstrap CSS -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
<style>
    body {
        display: flex;
        justify-content: center;
        align-items: flex-start;
        height: 100vh;
        background-image: url('https://img.freepik.com/free-photo/arrangement-black-friday-shopping-carts-with-copy-space_23-2148667047.jpg');
        background-size: cover;
        background-position: center;
    }
    .registration-container {
        background-color: rgba(255, 255, 255, 0.9);
        padding: 2rem;
        border-radius: 10px;
        box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        width: 100%;
        max-width: 500px;
        margin-top: 50px;
    }
    .registration-container h2 {
        margin-bottom: 1.5rem;
        text-align: center;
        font-weight: 600;
    }
    .login-link {
        display: block;
        margin-top: 1rem;
        text-align: center;
    }
    .error {
        color: red;
        font-size: 0.875rem;
    }
</style>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
    $(document).ready(function() {
        $('#role').change(function() {
            if ($(this).val() == 'seller') {
                $('#sellerFields').show();
                if ($(window).scrollTop() + $(window).height() < $('#sellerFields').offset().top + $('#sellerFields').height()) {
                    $('html, body').animate({
                        scrollTop: $("#sellerFields").offset().top
                    }, 500);
                }
            } else {
                $('#sellerFields').hide();
            }
        });

        $('form').on('submit', function(e) {
            let email = $('#email').val();
            let password = $('#password').val();
            let emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            let passwordPattern = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}$/;

            if (!emailPattern.test(email)) {
                $('#emailError').show();
                e.preventDefault();
                return false;
            } else {
                $('#emailError').hide();
            }

            if (!passwordPattern.test(password)) {
                $('#passwordError').show();
                e.preventDefault();
                return false;
            } else {
                $('#passwordError').hide();
            }
        });
    });
</script>
</head>
<body>
<div class="registration-container">
    <h2>User Registration</h2>
    <form action="register" method="POST">
        <div class="form-group">
            <label for="name">Name</label>
            <input type="text" class="form-control" id="name" name="name" required>
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <input type="email" class="form-control" id="email" name="email" required>
            <div id="emailError" class="error" style="display:none;">Please enter a valid email address.</div>
            <%
                String emailError = (String) request.getAttribute("emailError");
                if (emailError != null) {
                    out.print("<div class='error'>" + emailError + "</div>");
                }
            %>
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" name="password" required>
            <div id="passwordError" class="error" style="display:none;">Password must be at least 8 characters long and include at least one uppercase letter, one lowercase letter, and one digit.</div>
            <%
                String passwordError = (String) request.getAttribute("passwordError");
                if (passwordError != null) {
                    out.print("<div class='error'>" + passwordError + "</div>");
                }
            %>
        </div>
        <div class="form-group">
            <label for="role">Role</label>
            <select class="form-control" id="role" name="role" required>
                <option value="buyer">Buyer</option>
                <option value="seller">Seller</option>
            </select>
        </div>
        <div id="sellerFields" style="display:none;">
            <div class="form-group">
                <label for="bus_name">Business Name</label>
                <input type="text" class="form-control" id="bus_name" name="bus_name">
            </div>
            <div class="form-group">
                <label for="bus_address">Business Address</label>
                <input type="text" class="form-control" id="bus_address" name="bus_address">
            </div>
        </div>
        <div class="form-group">
            <label for="address">Address</label>
            <textarea class="form-control" id="address" name="address"></textarea>
        </div>
        <div class="form-group">
            <label for="phone_number">Phone Number</label>
            <input type="text" class="form-control" id="phone_number" name="phone_number">
        </div>
        <button type="submit" class="btn btn-primary btn-block">Submit</button>
    </form>
    <a href="Login.jsp" class="login-link">Already have an account? Login here</a>
</div>
</body>
</html>
