package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Order;
import model.Product;
import model.Seller;
import model.User;
import service.OrderService;
import service.ProductService;
import service.UserService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private OrderService orderService;
    private ProductService productService;
    private UserService userService;

    public OrderServlet() {
        super();
        this.orderService = new OrderService();
        this.productService = new ProductService();
        this.userService = new UserService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getUserId();

        // Get the order history for the user
        List<Order> orderHistory = orderService.getOrderHistoryByUserId(userId);

        // Set the order history as a request attribute
        request.setAttribute("orderHistory", orderHistory);

        // Forward the request to the order history JSP page
        request.getRequestDispatcher("orderHistory.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        String shippingAddress = request.getParameter("shippingAddress");
        String billingAddress = request.getParameter("billingAddress");
        int userId = user.getUserId();
        user = userService.getUser(userId);

        Map<Integer, Integer> productQuantities = orderService.getQuantitiesByUserId(user.getUserId());
        int orderId = orderService.placeOrderfromCart(userId, shippingAddress, billingAddress);
        
        // Email content for the buyer
        String recipient = user.getEmail();
        String subject = "Order Placed from RevShop";
        StringBuilder sb = new StringBuilder();
        sb.append("Hello " + user.getUsername() + ",\n\nYour order has been placed successfully. Here are the details:\n\n");
        for (Map.Entry<Integer, Integer> entry : productQuantities.entrySet()) {
            try {
                Product product = productService.getProductDetailsbyId(entry.getKey());
                sb.append("Product Name: " + product.getName() + "\nQuantity: " + entry.getValue() + "\nPrice: " + product.getPrice() + "\n\n");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        String content = sb.toString();

        // Email configuration
        String host = "smtp.gmail.com";
        final String username = "chaitanya6746";
        final String password = "fbkh dojx rvif bnjq";

        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session emailSession = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Send email to buyer
            Message message = new MimeMessage(emailSession);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(content);
            Transport.send(message);

            // Send email to seller
            for (Map.Entry<Integer, Integer> entry : productQuantities.entrySet()) {
                Product product = productService.getProductDetailsbyId(entry.getKey());
                System.out.println(product);
                int sellerId = product.getSeller_id();
                System.out.println(sellerId);
                User user1 = userService.getSellerById(sellerId);
                String sellerEmail = user1.getEmail();
                
                String sellerSubject = "Product Sold on RevShop";
                StringBuilder sellerSb = new StringBuilder();
                sellerSb.append("Hello " + user1.getUsername() + ",\n\nYou have sold the following product:\n\n");
                sellerSb.append("Product Name: " + product.getName() + "\nQuantity: " + entry.getValue() + "\n\n");

                Message sellerMessage = new MimeMessage(emailSession);
                sellerMessage.setFrom(new InternetAddress(username));
                sellerMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(sellerEmail));
                sellerMessage.setSubject(sellerSubject);
                sellerMessage.setText(sellerSb.toString());
                Transport.send(sellerMessage);
            }

            request.setAttribute("productQuantities", productQuantities);
            request.setAttribute("orderId", orderId);
            request.getRequestDispatcher("OrderConfirm.jsp").forward(request, response);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
