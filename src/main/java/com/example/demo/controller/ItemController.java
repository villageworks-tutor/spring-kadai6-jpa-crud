package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Item;
import com.example.demo.repository.ItemRepository;





@Controller
public class ItemController {
	
	@Autowired
	ItemRepository itemRepository;
	
	// 商品一覧画面表示
	@GetMapping("/items")
	public String index(Model model) {
		List<Item> itemList = itemRepository.findAll();
		model.addAttribute("itemList", itemList);
		return "items";
	}
	
	// 商品登録画面表示
	@GetMapping("/items/add")
	public String create() {
		return "addItem";
	}
	
	// 商品登録処理
	@PostMapping("/items/add")
	public String store(
			@RequestParam(name = "categoryId", defaultValue = "") Integer categoryId,
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "price", defaultValue = "") Integer price) {
		// リクエストパラメータから登録する商品をインスタンス化
		Item item = new Item();
		item.setCategoryId(categoryId);
		item.setName(name);
		item.setPrice(price);
		// 商品を登録
		itemRepository.save(item);
		// 商品一覧画面表示にリダイレクト
		return "redirect:/items";
	}
	
	// 商品更新画面表示
	@GetMapping("/items/{id}/edit")
	public String edit(
			@PathVariable("id") Integer id,
			Model model) {
		// パスパラメータをもとに更新対象の商品をデータベースから取得
		Item item = itemRepository.findById(id).get();
		// 取得した商品をスコープに登録
		model.addAttribute("item", item);
		// 画面遷移
		return "editItem";
	}
	
	// 商品更新処理
	@PostMapping("/items/{id}/edit")
	public String update(
			@PathVariable("id") Integer id,
			@RequestParam(name = "categoryId", defaultValue = "") Integer categoryId,
			@RequestParam(name = "name", defaultValue = "") String name,
			@RequestParam(name = "price", defaultValue = "") Integer price) {
		// リクエストパラメータから更新する商品をインスタンス化
		Item item = new Item();
		item.setId(id);
		item.setCategoryId(categoryId);
		item.setName(name);
		item.setPrice(price);
		// インスタンス化した商品を永続化
		itemRepository.save(item);
		// 商品一覧画面表示にリダイレクト
		return "redirect:/items";
	}
	
	@PostMapping("/items/{id}/delete")
	public String delete(@PathVariable("id") Integer id) {
		// パスパラメータをもとに商品を削除
		itemRepository.deleteById(id);
		// 商品一覧画面表示にリダイレクト
		return "redirect:/items";
	}
	
	
}
