package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Category;
import com.example.demo.repository.CategoryRepository;





@Controller
public class CategoryController {
	
	@Autowired
	CategoryRepository categoryRepository;
	
	// カテゴリー一覧画面表示
	@GetMapping("/categories")
	public String index(Model model) {
		// すべてのカテゴリーをデータベースから取得
		List<Category> categoryList = categoryRepository.findAll();
		// 取得したカテゴリーリストをスコープに登録
		model.addAttribute("categoryList", categoryList);
		// 画面遷移
		return "category/list";
	}
	
	// カテゴリー登録画面表示
	@GetMapping("/categories/add")
	public String entry(
			@RequestParam(name = "mode", defaultValue = "") String mode,
			Model model) {
		// 処理モードをスコープに登録
		model.addAttribute("mode", mode);
		// 画面遷移
		return "category/entry";
	}
	
	// カテゴリー確認画面表示
	@PostMapping("/categories/confirm")
	public String confirm(
			@RequestParam(name = "mode", defaultValue = "") String mode,
			@RequestParam(name = "name", defaultValue = "") String name,
			Model model) {
		// リクエストパラメータから登録するカテゴリーをインスタンス化
		Category category = new Category(name);
		// インスタンス化したカテゴリーをスコープに登録
		model.addAttribute("category", category);
		model.addAttribute("mode", mode);
		// カテゴリー一覧画面表示にリダイレクト
		return "category/confirm";
	}
	
	// カテゴリー登録処理
	@PostMapping("/categories/add")
	public String store(@RequestParam("name") String name) {
		// リクエストパラメータから登録するカテゴリーをインスタンス化
		Category category = new Category(name);
		// カテゴリーのインスタンスを永続化
		categoryRepository.save(category);
		// カテゴリー一覧画面表示にリダイレクト
		return "redirect:/categories";
	}
	
}
