package service;

import dao.SellerDAO;
import exception.InvalidInputException;
import exception.UserNotFoundException;
import model.Seller;

public class SellerService {
	private SellerDAO sellerDAO = new SellerDAO();

    public void createSeller(Seller seller) throws InvalidInputException {
    	if (seller.getBus_adress().isEmpty()||seller.getBusiness_name().isEmpty()){
            throw new InvalidInputException("All fields are required");
        }
        sellerDAO.addSeller(seller);
    }
    public Seller getDetails(int id) throws InvalidInputException {
    	Seller seller = sellerDAO.getBusinessDetailsById(id);
    	return seller;
    }
}

    
//    public Seller loginSeller(String username, String password) throws UserNotFoundException {
//        Seller seller = SellerDAO.getSellerByUsername(username);
//        if (seller == null || !seller.getPassword().equals(password)) {
//            throw new UserNotFoundException("Invalid username or password.");
//        }
//        return seller;
//    }
