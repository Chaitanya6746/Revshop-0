package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Product;
import model.Seller;
import model.User;
import service.ProductService;
import service.SellerService;
import service.UserService;
import util.PasswordUtil;

import java.io.IOException;
import java.util.List;

import exception.UserNotFoundException;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService userService ;
    private SellerService sellerService;
    private ProductService productService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
        this.userService = new UserService();
        this.sellerService = new SellerService();
        this.productService = new ProductService();
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
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		System.out.println(email+" "+password);
		
//		String Role = (String) request.getAttribute("Role");
	        
	        try {
	        	User user = userService.loginUserbymail(email, PasswordUtil.hashPassword(password));
	        	
//	        	user = userService.loginSeller(name, password);
	        	if(user.getRole().equals("buyer")) {
	        		HttpSession session = request.getSession();
	        		session.setAttribute("user",user);
	        		request.setAttribute("User", user);
	                 request.getRequestDispatcher("Buyer_Dashboard.jsp").forward(request, response);
	        	}
	        	
	            else if (user.getRole().equals("seller")) {
	            	Seller seller = sellerService.getDetails(user.getUserId());
	            	HttpSession session = request.getSession();
	        		session.setAttribute("seller",seller);
	        		List<Product> products = productService.getproductBySellerId(seller.getSellerId());
	        		request.setAttribute("products", products);
	                // Forward the request to the JSP page
	                request.getRequestDispatcher("Seller_Dashboard.jsp").forward(request, response);
	                
	            }
	        	
	            
	        } catch (Exception e) {
	            String error = "Invalid Email or Password";
	            request.setAttribute("error", error);
		        // Forward the request to the JSP page
		        request.getRequestDispatcher("Login.jsp").forward(request, response);
	        }
	}
}
