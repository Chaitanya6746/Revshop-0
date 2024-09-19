package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Category;
import model.Product;
import model.Seller;
import service.CategoryService;
import service.ProductService;

import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class ModifyProductServlet
 */
@WebServlet("/ModifyProductServlet")
public class ModifyProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductService productService;
	private CategoryService categoryService;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModifyProductServlet() {
        super();
        // TODO Auto-generated constructor stub
        this.productService = new ProductService();
        this.categoryService = new CategoryService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		Seller seller = (Seller) session.getAttribute("seller");
		
		if (seller != null) {
		    String action = request.getParameter("action");

		    switch (action) {
		        case "Modify":
		            handlemodifyProduct(request, response, seller);
		            break;
		        case "Update":
		            handleUpdateInventory(request, response, seller);
		            break;
		        case "Delete":
		            handleDeleteProduct(request, response, seller);
		            break;
//		        case "Logout":
//		            response.sendRedirect("/Login.jsp");
//		            break;
		        default:
		            response.sendRedirect("Seller_Dashboard.jsp");
		            break;
		    }
		} else {
		    // Handle the case where the seller is not found in session
		    response.sendRedirect("Login.jsp"); // Or another appropriate action
		}
		
		
		
			
	}

	private void handleDeleteProduct(HttpServletRequest request, HttpServletResponse response, Seller seller) throws IOException {
		// TODO Auto-generated method stub
		try {
	        // Get and parse the productId parameter
	        String productIdParam = request.getParameter("productId");
	        if (productIdParam == null || productIdParam.trim().isEmpty()) {
	            throw new IllegalArgumentException("Product ID is missing.");
	        }
	        int productId = Integer.parseInt(productIdParam);
	        productService.removeProductById(productId);
	        List<Product> products = productService.getproductBySellerId(seller.getSellerId());
    		request.setAttribute("products", products);
            // Forward the request to the JSP page
            request.getRequestDispatcher("Seller_Dashboard.jsp").forward(request, response);
	        
		}
	        catch (NumberFormatException e) {
		        e.printStackTrace();
		        response.getWriter().println("Error updating inventory: Invalid number format.");
	        } catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	private void handleUpdateInventory(HttpServletRequest request, HttpServletResponse response, Seller seller) throws ServletException, IOException {

	    try {
	        // Get and parse the productId parameter
	        String productIdParam = request.getParameter("productId");
	        if (productIdParam == null || productIdParam.trim().isEmpty()) {
	            throw new IllegalArgumentException("Product ID is missing.");
	        }
	        int productId = Integer.parseInt(productIdParam);

	        // Get other parameters
	        String productName = request.getParameter("productName");
	        String productDescription = request.getParameter("productDescription");

	        String productPriceParam = request.getParameter("productPrice");
	        if (productPriceParam == null || productPriceParam.trim().isEmpty()) {
	            throw new IllegalArgumentException("Product price is missing.");
	        }
	        double productPrice = Double.parseDouble(productPriceParam);

	        String discountPriceParam = request.getParameter("discountPrice");
	        Double discountPrice = null;
	        if (discountPriceParam != null && !discountPriceParam.trim().isEmpty()) {
	            discountPrice = Double.parseDouble(discountPriceParam);
	        }

	        String quantityAvailableParam = request.getParameter("quantityAvailable");
	        if (quantityAvailableParam == null || quantityAvailableParam.trim().isEmpty()) {
	            throw new IllegalArgumentException("Quantity available is missing.");
	        }
	        int quantityAvailable = Integer.parseInt(quantityAvailableParam);

	        String thresholdQuantityParam = request.getParameter("thresholdQuantity");
	        Integer thresholdQuantity = null;
	        if (thresholdQuantityParam != null && !thresholdQuantityParam.trim().isEmpty()) {
	            thresholdQuantity = Integer.parseInt(thresholdQuantityParam);
	        }

	        String imageUrl = request.getParameter("imageUrl");

	        String categoryIdParam = request.getParameter("productCategory");
	        if (categoryIdParam == null || categoryIdParam.trim().isEmpty()) {
	            throw new IllegalArgumentException("Category ID is missing.");
	        }
	        int categoryId = Integer.parseInt(categoryIdParam);

	        // Create a Product object and set its properties
	        Product product = new Product();
	        product.setName(productName);
	        product.setDescription(productDescription);
	        product.setImg_url(imageUrl);
	        product.setPrice(productPrice);
	        product.setDis_price(discountPrice);
	        product.setQuantity(quantityAvailable);
	        product.setThres_quanty(thresholdQuantity);
	        product.setCategory_id(categoryId);

	        // Update product in the database
	        productService.updateInventoryById(productId, product);

	        // Forward to the dashboard or another page
//	        HttpSession session = request.getSession();
//    		session.setAttribute("seller",seller);
    		List<Product> products = productService.getproductBySellerId(seller.getSellerId());
    		request.setAttribute("products", products);
            // Forward the request to the JSP page
            request.getRequestDispatcher("Seller_Dashboard.jsp").forward(request, response);

	    } catch (NumberFormatException e) {
	        e.printStackTrace();
	        response.getWriter().println("Error updating inventory: Invalid number format.");
	    } catch (IllegalArgumentException e) {
	        e.printStackTrace();
	        response.getWriter().println("Error updating inventory: " + e.getMessage());
	    } catch (Exception e) {
	        e.printStackTrace();
	        response.getWriter().println("Error updating inventory: " + e.getMessage());
	    }
	}


	

	private void handlemodifyProduct(HttpServletRequest request, HttpServletResponse response, Seller seller) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Product product = new Product();
		HttpSession session = request.getSession();
		seller = (Seller) session.getAttribute("seller");
		product.setSeller_id(seller.getSellerId());
		int product_id = Integer.parseInt(request.getParameter("productId"));
		product = productService.getProductDetailsbyId(product_id);
		List<Category> categories = categoryService.showCategories();
        // Ensure the products attribute is correctly set
    	request.setAttribute("categories", categories);
        // Forward the request to the JSP page
		request.setAttribute("product", product);
        // Forward the request to the JSP page
        request.getRequestDispatcher("updateproduct.jsp").forward(request, response);
	}

}
