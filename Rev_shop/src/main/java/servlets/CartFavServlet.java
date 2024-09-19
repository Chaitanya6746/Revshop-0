package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Cart;
import model.Category;
import model.Product;
import model.Seller;
import model.User;
import service.CartService;
import service.CategoryService;
import service.FavService;
import service.ProductService;

import java.io.IOException;
import java.util.List;

/**
 * Servlet implementation class CartFavServlet
 */
@WebServlet("/CartFavServlet")
public class CartFavServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoryService categoryService;
	private ProductService productService;
	private CartService cartService;
	private FavService favService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartFavServlet() {
        super();
        // TODO Auto-generated constructor stub
        this.categoryService = new CategoryService();
        this.productService = new ProductService();
        this.cartService = new CartService();
        this.favService = new FavService();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
    	HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		if (user != null) {
		    String action = request.getParameter("action");

		    switch (action) {
		        case "ManageCart":
		        	handleCart(request, response,user);
		            break;
		        case "show_category":
		        	List<Category> categories = categoryService.showCategories();
		            // Ensure the products attribute is correctly set
		        	request.setAttribute("categories", categories);
		            break;
		        case "showfavs":
		        	List<Product> favitems = favService.showfavs(user.getUserId());
		    		request.setAttribute("favitems", favitems);
		    		request.getRequestDispatcher("favs.jsp").forward(request, response);
		        default:
		            response.sendRedirect("Seller_Dashboard.jsp");
		            break;
		    }
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
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		if (user != null) {
		    String action = request.getParameter("action");

		    switch (action) {
		        case "selectCat":
		            selectProductsCategory(request, response, user);
		            break;
		        case "addtoCart":
		            handleaddToCart(request, response, user);
		            break;
		        case "removeFromCart":
		            deleteProductfromCart(request, response, user);
		            break;
		            
		        case "addtoFavs":
		            handleFavs(request, response,user);
		            break;
		        case "removeFromFavs":
		        	deletefromFavs(request, response,user);
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

	private void deletefromFavs(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String product_idString = request.getParameter("productId");
		int product_id = Integer.parseInt(product_idString);
		favService.removeFavs(user.getUserId(),product_id);
		List<Product> favitems = favService.showfavs(user.getUserId());
		request.setAttribute("favitems", favitems);
		request.getRequestDispatcher("favs.jsp").forward(request, response);
	}

	private void handleFavs(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String product_idString = request.getParameter("productId");
		int product_id = Integer.parseInt(product_idString);
		favService.addTofavs(user.getUserId(),product_id);
		List<Product> favitems = favService.showfavs(user.getUserId());
		request.setAttribute("favitems", favitems);
		request.getRequestDispatcher("favs.jsp").forward(request, response);
		
	}

	private void handleCart(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<Product> cartitems = cartService.showCartItems(user.getUserId());
		request.setAttribute("cartitems", cartitems);
		request.getRequestDispatcher("Cart.jsp").forward(request, response);
//		request.getRequestDispatcher("view-products.jsp").forward(request, response);
	}

	private void deleteProductfromCart(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String product_idString = request.getParameter("productId");
		int product_id = Integer.parseInt(product_idString);
		cartService.removeFromCart(user.getUserId(),product_id);
		List<Product> cartitems = cartService.showCartItems(user.getUserId());
		request.setAttribute("cartitems", cartitems);
		request.getRequestDispatcher("Cart.jsp").forward(request, response);
	}

	private void handleaddToCart(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String product_idString = request.getParameter("productId");
		int product_id = Integer.parseInt(product_idString);
		String quant_String = request.getParameter("quantity");
		int quanty = Integer.parseInt(quant_String);
//		System.out.println(product_id+" "+quanty);
		Cart cart = new Cart();
		cart.setProduct_id(product_id);
		cart.setUser_id(user.getUserId());
		cart.setQuantity(quanty);
		cartService.addToCart(user.getUserId(), product_id, cart);
//		int cartquant = cartService.getQuantfromCart(product_id);
		List<Product> cartitems = cartService.showCartItems(user.getUserId());
		request.setAttribute("cartitems", cartitems);
		request.getRequestDispatcher("Cart.jsp").forward(request, response);
		
	}

	private void selectProductsCategory(HttpServletRequest request, HttpServletResponse response, User user) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String cat_idString = request.getParameter("productcat");
		
		int cat_id = Integer.parseInt(cat_idString);
		List<Product> products = productService.getProductDetails(cat_id);
		request.setAttribute("products", products);
		List<Category> categories = categoryService.showCategories();
        // Ensure the products attribute is correctly set
    	request.setAttribute("categories", categories);
		request.getRequestDispatcher("view-products.jsp").forward(request, response);
	}

}
