package service;

import dao.UserDAO;
import exception.InvalidInputException;
import exception.UserNotFoundException;
import model.User;

public class UserService {
    private UserDAO userDAO = new UserDAO();

    public void createUser(User user) throws InvalidInputException {
    	if (user.getUsername().isEmpty() || user.getEmail().isEmpty() || user.getPassword().isEmpty()) {
            throw new InvalidInputException("All fields are required.");
        }
        userDAO.addUser(user);
    }

    public User loginUser(String username, String password) throws UserNotFoundException {
        User user = UserDAO.getUserByUsername(username);
        if (user == null || !user.getPassword().equals(password)||!user.getRole().equals("buyer")) {
            throw new UserNotFoundException("Invalid username or password.");
        }
        return user;
    }

	public User loginSeller(String username, String password) throws UserNotFoundException {
		// TODO Auto-generated method stub
		User user = UserDAO.getUserByUsername(username);
        if (user == null || !user.getPassword().equals(password)||!user.getRole().equals("seller")) {
            throw new UserNotFoundException("Invalid username or password.");
        }
        return user;
	}

    // Other CRUD methods can be added here
}
