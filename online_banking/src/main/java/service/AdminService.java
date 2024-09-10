package service;

import DAO.AdminDAO;
import exception.UserNotFoundException;
import model.Admin;

public class AdminService {
    private AdminDAO adminDAO = new AdminDAO();

    public void createAdmin(Admin admin) {
        adminDAO.addAdmin(admin);
    }

    public void deleteAdmin(int adminId) {
        adminDAO.removeAdmin(adminId);
    }

    public Admin loginAdmin(String username, String password) throws UserNotFoundException {
        Admin admin = adminDAO.getAdminByUsername(username);
        if (admin == null || !admin.getPassword().equals(password)) {
            throw new UserNotFoundException("Invalid username or password.");
        }
        return admin;
    }

    // Other CRUD methods can be added here
}
