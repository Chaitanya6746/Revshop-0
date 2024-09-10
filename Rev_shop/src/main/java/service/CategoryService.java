package service;

import dao.CategoryDAO;
import model.Category;

public class CategoryService {
	private CategoryDAO categoryDAO = new CategoryDAO();
	public int showCategories() {
		int category_id = categoryDAO.displayCategories();
		// TODO Auto-generated method stub
		return category_id;
	}
	
}
