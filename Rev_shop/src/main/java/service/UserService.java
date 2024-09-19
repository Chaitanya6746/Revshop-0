package service;

import java.sql.SQLException;

import dao.UserDAO;
import exception.InvalidInputException;
import exception.UserNotFoundException;
import model.Seller;
import model.User;

public class UserService {
    private UserDAO userDAO = new UserDAO();

    public int createUser(User user) throws InvalidInputException, SQLException {
    	if (user.getUsername().isEmpty() || user.getEmail().isEmpty() || user.getPassword().isEmpty()) {
            throw new InvalidInputException("All fields are required.");
        }
        int user_id = userDAO.addUser(user);
        return user_id;
    }

    public User loginUser(String username, String password) throws UserNotFoundException {
        User user = UserDAO.getUserByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            throw new UserNotFoundException("Invalid username or password.");
        }
        return user;
    }

	public User loginSeller(String username, String password) throws UserNotFoundException {
		// TODO Auto-generated method stub
		User user = UserDAO.getUserByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            throw new UserNotFoundException("Invalid username or password.");
        }
        return user;
	}
	public User getUser(int user_id) {
		return UserDAO.getUserById(user_id);
	}

	public User getSellerById(int sellerId) {
		// TODO Auto-generated method stub
		return UserDAO.getUserById(sellerId);
	}

	public User loginUserbymail(String email, String password) throws UserNotFoundException {
		// TODO Auto-generated method stub
		User user = UserDAO.getUserBymail(email);
		if (user == null || !user.getPassword().equals(password)) {
            throw new UserNotFoundException("Invalid username or password.");
        }
        return user;
	}	

    // Other CRUD methods can be added here
}
