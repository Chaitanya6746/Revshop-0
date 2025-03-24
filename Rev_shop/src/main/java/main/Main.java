package main;

import java.sql.SQLException;

import exception.InvalidInputException;
import exception.UserNotFoundException;
import model.Cart;
import model.Category;
import model.Order;
import model.Product;
import model.Seller;
import model.User;
import service.CartService;
import service.CategoryService;
import service.OrderService;
import service.ProductService;
import service.SellerService;
import service.UserService;
import util.InputUtil;
import util.PasswordUtil;

public class Main {
	public static void main(String[] args) throws SQLException {
		UserService userService =  new UserService();
		SellerService sellerService = new SellerService();
		ProductService productService = new ProductService();
		CategoryService categoryService = new CategoryService();		
		CartService cartService = new CartService();
		OrderService orderService  = new OrderService();
		boolean execute = true;
		
		while(execute) {
			System.out.println("1.User Registration\n2.Buyer Login\n3.Seller Login");
			int option = InputUtil.getInt("Choose an option: ");
			switch(option) {
			
			
				case 1:
					createUserRegistration(userService,sellerService);
					break;
				case 2:
					handleUserLogin(userService,productService,categoryService,cartService,orderService);
					break;
				case 3:
					handleSellerLogin(sellerService,userService,productService,categoryService);
					break;
				case 4:
					execute = false;
					break;
				default:
					System.out.println("Enter valid option ra jaffa");
			}
		}
	}

	private static void handleSellerLogin(SellerService sellerService, UserService userService,ProductService productService,CategoryService categoryService) {
		// TODO Auto-generated method stub
		String username = InputUtil.getString("Enter username: ");
		String password = InputUtil.getString("Enter password: ");
		try {
			User user = userService.loginSeller(username, PasswordUtil.hashPassword(password));
			Seller seller = new Seller();
			int seller_id = user.getUserId();
			seller.setSellerId(user.getUserId());
			if(sellerService.getDetails(seller.getSellerId())==null) {
				System.out.println("Hello "+user.getUsername()+",\nPlease provide seller Details: ");
				String business_name = InputUtil.getString("Enter business name: ");
				String bus_adress = InputUtil.getString("Enter business address: ");
				seller.setBusinessName(business_name);
				seller.setBusinessAdress(bus_adress);
				sellerService.createSeller(seller);
				boolean LogedIn = true;
				
				while(LogedIn) {
					System.out.println("1.Add Products\n2.Manage Inventory\n3.View Orders\n4.Logout");
					int option = InputUtil.getInt("Choose an option: ");
					switch(option) {
						case 1:
							addProductstoInventory(seller_id,sellerService,productService,categoryService);
							break;
						case 2:
							manageInvenotry(productService);
							break;
						case 3:
							seePlacedOrders(sellerService,userService);
							break;
						case 4:
							LogedIn = false;
							System.out.println("Logged out Succesfully");
							break;
						default:
							System.out.println("Enter valid option ra jaffa");
					}
				}
			}
			else {
				seller =  sellerService.getDetails(seller.getSellerId());
				System.out.println("Business_name: "+seller.getBusiness_name()+" Business_address: "
						+seller.getBus_adress());
				boolean LogedIn = true;
				
				while(LogedIn) {
					System.out.println("1.Add Products\n2.Manage Inventory\n3.View Orders\n4.Logout");
					int option = InputUtil.getInt("Choose an option: ");
					switch(option) {
						case 1:
							addProductstoInventory(seller_id,sellerService,productService, categoryService);
							break;
						case 2:
							manageInvenotry(productService);
							break;
						case 3:
							seePlacedOrders(sellerService,userService);
							break;
						case 4:
							LogedIn = false;
							System.out.println("Logged out Succesfully");
							break;
						default:
							System.out.println("Enter valid option ra jaffa");
					}
				}
			}
			
				
			
		}catch(UserNotFoundException | InvalidInputException e) {
			System.out.println(e.getMessage());
		}
		
	}

	private static void seePlacedOrders(SellerService sellerService, UserService userService) {
		// TODO Auto-generated method stub
		
	}

	private static void manageInvenotry(ProductService productService) {
		// TODO Auto-generated method stub
		boolean LogedIn = true;
		while(LogedIn) {
			System.out.println("1.Update products\n2.Remove Products\n3.See placed Orders\n4.Logout");
			int option = InputUtil.getInt("Choose an option: ");
			switch(option) {
				case 1:
					updateProduct(productService);
					break;
				case 2:
					removeProduct(productService);
					break;
				case 3:
//					seePlacedOrders();
					break;
				case 4:
					LogedIn = false;
					System.out.println("Logged out Succesfully");
					break;
				default:
					System.out.println("Enter valid option ra jaffa");
			}
		}
		
	}

	private static void updateProduct(ProductService productService) {
		// TODO Auto-generated method stub
		productService.showAllProducts();
		int product_id = InputUtil.getInt("Choose product to Update : ");
//		String update = "";
		boolean LogedIn = true;
		while(LogedIn) {
			System.out.println("Update the Inventory 1.Quantity \n2.Price \n3.Discount Price\n4.Logout");
			int option = InputUtil.getInt("Choose an option: ");
			switch(option) {
				case 1:
					int quantity =  InputUtil.getInt("Provide Updated quantity: ");
					productService.updateInventorybyquant(product_id,quantity);
					productService.showAllProducts();
					break;
				case 2:
					double price =  InputUtil.getDouble("Provide Updated price: ");
					productService.updateInventorybyprice(product_id,price);
					productService.showAllProducts();
					break;
				case 3:
					double dis_price =  InputUtil.getDouble("Provide Updated Discount_Price: ");
					productService.updateInventorybydis_price(product_id,dis_price);
					productService.showAllProducts();
					break;
				case 4:
					LogedIn = false;
					System.out.println("Logged out Succesfully");
					break;
				default:
					System.out.println("Enter valid option ra jaffa");
			}
		
		
		}
	}

	private static void removeProduct(ProductService productService) {
		// TODO Auto-generated method stub
		productService.showAllProducts();
		int product_id = InputUtil.getInt("Choose product to Remove : ");
		productService.removeProductById(product_id);
		productService.showAllProducts();
		
	}

	private static void addProductstoInventory(int seller_id,SellerService sellerService,ProductService productService,CategoryService categoryService) {
		// TODO Auto-generated method stub
		String prod_name = InputUtil.getString("Enter product Name: ");
		String desc = InputUtil.getString("Enter description: ");
		String img_url = InputUtil.getString("Enter Image URL: ");
		double price = InputUtil.getDouble("Enter Price: ");
		double dis_price = InputUtil.getDouble("Enter Discount Price: ");
		int quantity = InputUtil.getInt("Enter Quantity available: ");
		int  thes_quant = InputUtil.getInt("Enter Threshold Quantity: ");
//		int category_id = categoryService.showCategories();
//		Seller seller = seller.setSellerId(user.getUserId()); 
		Product product = new Product();
		product.setName(prod_name);		
		product.setDescription(desc);
		product.setImg_url(img_url);
		product.setPrice(price);
		product.setDis_price(dis_price);
		product.setQuantity(quantity);
		product.setThres_quanty(thes_quant);
//		
//		try {
////			productService.addProduct(seller_id,category_id,product);
//			System.out.println("Product added succesfully");
//		} catch (InvalidInputException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		
	}

	private static void handleUserLogin(UserService userService,ProductService productService,CategoryService categoryService,CartService cartService,OrderService orderService) {
		// TODO Auto-generated method stub
		String username = InputUtil.getString("Enter username: ");
		String password = InputUtil.getString("Enter password: ");
		int product_id =0;
		try {
			User user = userService.loginUser(username, PasswordUtil.hashPassword(password));
			System.out.println("Welcome "+ user.getUsername()+" Our Buyer");
			int user_id = user.getUserId();
			boolean LogedIn = true;
			while(LogedIn) {
				System.out.println("1.View All Products\n2.Add or Remove Product cart\n3.Browse Products by category\n4.Show Cart\n5.Logout");
				int option = InputUtil.getInt("Choose an option: ");
				switch(option) {
					case 1:
						viewProductsDetails(user_id,productService,cartService);
						break;
					case 2:
						addorRemoveProducts(user_id,productService,cartService,product_id);
						break;
					case 3:
						browseProducts(user_id,productService,categoryService,cartService);
						break;
					case 4:
						viewCartProducts(user_id,cartService,orderService);
						break;
					case 5:
						LogedIn = false;
						System.out.println("Logged out Succesfully");
						break;
					default:
						System.out.println("Enter valid option ra jaffa");
				}
			}
		}catch(UserNotFoundException e) {
			System.out.println(e.getMessage());
		}	
	}

	private static void viewCartProducts(int user_id, CartService cartService,OrderService orderService) {
		// TODO Auto-generated method stub
		cartService.showCartItems(user_id);
		int option = InputUtil.getInt("Choose an option: ");
		switch(option) {
		case 1:
			placeOrder(user_id,cartService,orderService);
			break;
		case 2:
//			manageProductCart(user_id,cartService);
			break;
		default:
			System.out.println("Enter valid option ra jaffa");
	}	
}

	private static void placeOrder(int user_id, CartService cartService, OrderService orderService) {
		// TODO Auto-generated method stub
		String shipping_add = "";
		String billing_add = "";
		orderService.placeOrderfromCart(user_id,shipping_add,billing_add);
		
		
	}

	private static void browseProducts(int user_id,ProductService productService,CategoryService categoryService,CartService cartService) {
		// TODO Auto-generated method stub
//		int cat_id = categoryService.showCategories();
//		productService.getProductDetails(cat_id);
		int product_id = InputUtil.getInt("Choose to get product details: ");
		productService.getProductDetailsbyId(product_id);
		
		addorRemoveProducts(user_id,productService, cartService, product_id);
		
	}

	private static void addorRemoveProducts(int user_id,ProductService productService,CartService cartService,int product_id) {
		// TODO Auto-generated method stub
		System.out.println("1.Add to Cart\n2.Remove product\n3.Exit");
		int option = InputUtil.getInt("Choose option: ");
		Cart cart = new Cart();
			switch(option) {
			case 1:
				int quantity = InputUtil.getInt("Enter Quantity: ");
				cart.setQuantity(quantity);
				cartService.addToCart(user_id,product_id,cart);
				System.out.println("Product Added to Cart");
				break;
			case 2:
//				cartService.removeFromCart(user_id);
				System.out.println("Product Removed to Cart");
				break;
			case 3:
				System.out.println("Exit");
				break;
			default:
				System.out.println("Enter valid option ra jaffa");
		}
	}
	private static void viewProductsDetails(int user_id,ProductService productService,CartService cartService) {
		// TODO Auto-generated method stub
		productService.showAllProducts();
		int product_id = InputUtil.getInt("Choose product to Add to cart: ");
		addorRemoveProducts(user_id,productService, cartService, product_id);

		
	}

	private static void createUserRegistration(UserService userService, SellerService sellerService) throws SQLException {
		// TODO Auto-generated method stub
		String username = InputUtil.getString("Enter username: ");
		String password = InputUtil.getString("Enter password: ");
		String email = InputUtil.getString("Enter email: ");
		int role = InputUtil.getInt("1.Buyer\n2.Seller ");
		String Role = "";
		switch(role) {
			case 1:
				Role = "buyer";
				break;
			case 2:
				Role = "seller";
				break;
			default:
				System.out.println("Only buyer and sellers");
		}
		String address = InputUtil.getString("Enter Address: ");
		long phone_num = InputUtil.getLong("Enter Phone number: ");
		User user = new User();
		user.setUsername(username);
		user.setPassword(PasswordUtil.hashPassword(password));
		user.setEmail(email);
		user.setRole(Role);
		user.setaddress(address);
		user.setphonenumber(phone_num);
		try {
			userService.createUser(user);
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
