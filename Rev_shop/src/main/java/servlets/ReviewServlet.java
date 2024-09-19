package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Product;
import model.Review;
import model.User;
import service.OrderService;
import service.ReviewService;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;


/**
 * Servlet implementation class ReviewServlet
 */
@WebServlet("/review")
public class ReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderService orderService = new OrderService();
    private ReviewService reviewService = new ReviewService();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String viewReview = request.getParameter("action");
		if("showreview".equals(viewReview)) {
			List<Review> reviews = reviewService.getAllReview();
			System.out.println(reviews);
			request.setAttribute("reviews", reviews);
	        request.getRequestDispatcher("viewreviews.jsp").forward(request, response);
			
		}
		else {
			String orderIdString = request.getParameter("orderId");
			int orderId = Integer.parseInt(orderIdString);
	        List<Product> products = orderService.getOrderItems(orderId);
	        request.setAttribute("products", products);
	        request.getRequestDispatcher("/reviewForm.jsp").forward(request, response);
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        HttpSession session = request.getSession();
	        User user = (User) session.getAttribute("user");
	        int userId = user.getUserId();

	        // Get all parameters
	        Enumeration<String> parameterNames = request.getParameterNames();

	        while (parameterNames.hasMoreElements()) {
	            String paramName = parameterNames.nextElement();

	            if (paramName.equals("productIds")) {
	                String[] productIds = request.getParameterValues("productIds");
	                String[] ratings = request.getParameterValues("ratings");
	                String[] comments = request.getParameterValues("comments");

	                for (int i = 0; i < productIds.length; i++) {
	                    int productId = Integer.parseInt(productIds[i]);
	                    int rating = Integer.parseInt(ratings[i]);
	                    String comment = comments[i];

	                    // Add each review to the database
	                    reviewService.addReview(userId, productId, rating, comment);
	                }
	            }
	        }
	        
	        response.sendRedirect("view-products");
	    }

}
