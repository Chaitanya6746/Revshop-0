package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Product;
import model.Seller;
import service.ProductService;

import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class AddProductstoInventory
 */
@WebServlet("/AddProductServlet")
public class AddProductstoInventory extends HttpServlet {
	private static final long serialVersionUID = 1L;
       private ProductService productService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddProductstoInventory() {
        super();
        // TODO Auto-generated constructor stub
        this.productService = new ProductService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Seller seller = (Seller) session.getAttribute("seller");
		if (seller != null) {
			int seller_id = seller.getSellerId();
			List<Product> products = productService.getproductBySellerId(seller_id);
			request.setAttribute("products", products);
	        // Forward the request to the JSP page
	        request.getRequestDispatcher("Seller_Dashboard.jsp").forward(request, response);
		} else {
		    // Handle the case where the seller is not found in session
		    response.sendRedirect("Login.jsp"); // Or another appropriate action
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 String productName = request.getParameter("productName");
		    String productDescription = request.getParameter("productDescription");
		    double productPrice = Double.parseDouble(request.getParameter("productPrice"));
		    Double discountPrice = request.getParameter("discountPrice") != null ? Double.parseDouble(request.getParameter("discountPrice")) : null;
		    int quantityAvailable = Integer.parseInt(request.getParameter("quantityAvailable"));
		    Integer thresholdQuantity = request.getParameter("thresholdQuantity") != null ? Integer.parseInt(request.getParameter("thresholdQuantity")) : null;
		    String imageUrl = request.getParameter("imageUrl");
		    int categoryId = Integer.parseInt(request.getParameter("productCategory"));
		    
		    Product product = new Product();
		    product.setName(productName); // Example seller_id; replace with actual value
	        product.setDescription(productDescription);
	        product.setImg_url(imageUrl);
	        product.setPrice(productPrice);
	        product.setDis_price(discountPrice);
	        product.setQuantity(quantityAvailable);
	        product.setThres_quanty(thresholdQuantity);
	        product.setCategory_id(categoryId);
	        HttpSession session = request.getSession();
			Seller seller = (Seller) session.getAttribute("seller");
			product.setSeller_id(seller.getSellerId());
			try {
				productService.addProduct(product.getSeller_id(),product.getCategory_id(), product);
				List<Product> products = productService.getproductBySellerId(product.getSeller_id());
        		request.setAttribute("products", products);
                // Forward the request to the JSP page
                request.getRequestDispatcher("Seller_Dashboard.jsp").forward(request, response);
			}catch(Exception e) {
	            e.printStackTrace();
	            response.getWriter().println("Error registering user: " + e.getMessage());
	        }
	}

}
