	package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Category;
import model.Order;
import model.Seller;
import service.CategoryService;
import service.OrderService;
import service.ProductService;
import service.SellerService;

import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class SellerOpsServlet
 */
@WebServlet("/SellerOpsServlet")
public class SellerOpsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private SellerService sellerService;
	    private ProductService productService;
	    private CategoryService categoryService;
	    private OrderService orderService;

	    @Override
	    public void init() throws ServletException {
	        // Initialize services
	        sellerService = new SellerService();
	        productService = new ProductService();
	        categoryService = new CategoryService();
	        orderService = new OrderService();
	    }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SellerOpsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Seller seller = (Seller) session.getAttribute("seller");
		if(seller!=null) {
			List<Order> order =  orderService.getALlOrder();
			request.setAttribute("order", order);
	        // Forward the request to the JSP page
	        request.getRequestDispatcher("PlacedOrder.jsp").forward(request, response);
		}
		else {
			response.sendRedirect("Login.jsp");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Seller seller = (Seller) session.getAttribute("seller");
		
		if (seller != null) {
		    String action = request.getParameter("action");

		    switch (action) {
		        case "Add Products":
		            handleAddProducts(request, response, seller);
		            break;
		        case "Logout":
		            handleLogout(request, response);
		            break;
		        default:
		            response.sendRedirect("Seller_Dashboard.jsp");
		            break;
		    }
		} else {
		    // Handle the case where the seller is not found in session
		    response.sendRedirect("Login.jsp"); // Or another appropriate action
		}
	}

	    private void handleAddProducts(HttpServletRequest request, HttpServletResponse response, Seller seller) throws ServletException, IOException {
	        // Redirect to add_products.jsp
	    	List<Category> categories = categoryService.showCategories();
	        // Ensure the products attribute is correctly set
	    	request.setAttribute("categories", categories);
	        // Forward the request to the JSP page
	        request.getRequestDispatcher("add_products.jsp").forward(request, response);
	    }
	    private void handleLogout(HttpServletRequest request, HttpServletResponse response) throws IOException {
	        // Invalidate the session and redirect to login page
	        HttpSession session = request.getSession();
	        session.invalidate();
	        response.sendRedirect("Login.jsp");
	    }
	}

