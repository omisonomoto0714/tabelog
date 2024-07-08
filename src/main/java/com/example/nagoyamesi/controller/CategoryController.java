package com.example.nagoyamesi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.nagoyamesi.entity.Category;
import com.example.nagoyamesi.form.CategoryRegisterForm;
import com.example.nagoyamesi.repository.CategoryRepository;
import com.example.nagoyamesi.service.CategoryService;

@Controller
@RequestMapping("/admin/restaurants/category")
public class CategoryController {
	private final CategoryRepository categoryRepository;
	private final CategoryService categoryService;

	public CategoryController(CategoryRepository categoryRepository, CategoryService categoryService) {
		this.categoryRepository = categoryRepository;
		this.categoryService = categoryService;

	}

	//カテゴリの一覧
	@GetMapping
	public String index(@RequestParam(name = "keyword", required = false) String keyword,

			@PageableDefault(page = 0, size = 10, sort = "id", direction = Direction.ASC) Pageable pageable,
			Model model) {
		Page<Category> categoryPage;

		if (keyword != null && !keyword.isEmpty()) {
			categoryPage = categoryRepository.findByCategoryNameLike(
					"%" + keyword + "%", pageable);
		} else {
			categoryPage = categoryRepository.findAll(pageable);
		}

		model.addAttribute("categoryPage", categoryPage);
		model.addAttribute("keyword", keyword);
		model.addAttribute("categoryRegisterForm", new CategoryRegisterForm());

		return "admin/restaurants/category";
	}

	@PostMapping("/create")
	public String careate(@ModelAttribute @Validated CategoryRegisterForm categoryRegisterForm,
			BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		//		//カテゴリ登録済みか確認
		//		if (categoryService.isCategoryNameRegistered(categoryRegisterForm.getCategoryName())) {
		//			FieldError fieldError = new FieldError(bindingResult.getObjectName(), "categoryName", "既に存在するカテゴリです。");
		//			bindingResult.addError(fieldError);
		//		}
		//		if (bindingResult.hasErrors()) {
		//			return "redirect:/admin/restaurants/category";
		//		}

		categoryService.create(categoryRegisterForm);
		redirectAttributes.addFlashAttribute("successMessage", "カテゴリ登録が完了しました。");

		return "redirect:/admin/restaurants/category";
	}

	@PostMapping("/{id}/delete")
	public String delete(@PathVariable(name = "id") Integer id, RedirectAttributes redirectAttributes) {
		categoryRepository.deleteById(id);

		redirectAttributes.addFlashAttribute("successMessage", "カテゴリを削除しました。");

		return "redirect:/admin/restaurants/category";
	}

}
