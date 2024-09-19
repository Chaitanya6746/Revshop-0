package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Category;
import model.Product;
import model.User;
import service.CategoryService;
import service.ProductService;

import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class ViewProducts
 */
@WebServlet("/view-products")
public class ViewProducts extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductService productService;
    private CategoryService categoryService;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewProducts() {
        super();
        // Initialize the ProductService instance
        this.productService = new ProductService(); 
        this.categoryService =  new CategoryService(); // Ensure ProductService is properly implemented and accessible
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if(user!=null) {
			List<Product> products = productService.showAllProducts();
	        // Ensure the products attribute is correctly set
	        request.setAttribute("products", products);
	        List<Category> categories = categoryService.showCategories();
	        // Ensure the products attribute is correctly set
	    	request.setAttribute("categories", categories);
	    	request.setAttribute("user", user.getUserId());
	        // Forward the request to the JSP page
	        request.getRequestDispatcher("view-products.jsp").forward(request, response);
		}
		else {
			response.sendRedirect("Login.jsp");
		}
        
        
        
    }


    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response); // Forward POST requests to GET handler
    }
}
