package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "categories")
@Data
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String name;
	
	/**
	 * デフォルトコンストラクタ：引数付きのコンストラクタを定義する場合はデフォルトコンストラクタの定義が必須！
	 */
	public Category() {}
	
	/**
	 * コンストラクタ
	 * @param name
	 */
	public Category(String name) {
		this.name = name;
	}
	
}
