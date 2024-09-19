package service;

import java.util.List;

import dao.CategoryDAO;
import model.Category;

public class CategoryService {
	private CategoryDAO categoryDAO = new CategoryDAO();
	public List<Category> showCategories() {
		return categoryDAO.displayCategories();
	}
	
}
