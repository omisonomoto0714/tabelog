package com.example.nagoyamesi.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.nagoyamesi.entity.Category;
import com.example.nagoyamesi.form.CategoryRegisterForm;
import com.example.nagoyamesi.repository.CategoryRepository;

@Service
public class CategoryService {
	private final CategoryRepository categoryRepository;

	public CategoryService(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Transactional
	public void create(CategoryRegisterForm categoryRegisterForm) {
		Category category = new Category();

		category.setCategoryName(categoryRegisterForm.getCategoryName());

		categoryRepository.save(category);
	}

	//	//カテゴリが登録してあるか
	//	public boolean isCategoryNameRegistered(String categoryName) {
	//		Category category = categoryRepository.findByCategoryName(categoryName);
	//		return category != null;
	//	}

}
