package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

import dao.UserDAO;
import model.Seller;
import model.User;
import service.SellerService;
import service.UserService;
import util.PasswordUtil;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public RegistrationServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get user data from request
    	
    	String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = PasswordUtil.hashPassword(request.getParameter("password"));
        String role = request.getParameter("role");
        String address = request.getParameter("address");
        long phoneNumber = Long.parseLong(request.getParameter("phone_number"));
        String bus_name = request.getParameter("bus_name");
        String bus_address = request.getParameter("bus_address");

        // Create a new User object
        User user = new User();
        user.setUsername(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setRole(role);
        user.setaddress(address);
        user.setphonenumber(phoneNumber);

        UserService userService = new UserService();

        try {
            // Register user in the database
            
            // Get the generated user ID
            int userId = userService.createUser(user);

            if ("seller".equals(role)) {
                // Create a new Seller object
                Seller seller = new Seller();
                seller.setSellerId(userId);
                seller.setBusinessName(bus_name);
                seller.setBusinessAdress(bus_address);

                SellerService sellerService = new SellerService();
                sellerService.createSeller(seller);
                
            }
            
            response.getWriter().println("User registered successfully!");
            response.sendRedirect("Login.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("Error registering user: " + e.getMessage());
        }
 }
}

