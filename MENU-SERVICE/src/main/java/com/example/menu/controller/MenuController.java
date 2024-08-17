package com.example.menu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.menu.model.Menu;
import com.example.menu.service.MenuService;

@RestController
@RequestMapping("/")
public class MenuController {
	
	@Autowired
	private MenuService menuService;
	
	@GetMapping("getMenu")
	public List<Menu> getMenu() {
		return menuService.getMenu();
	}
	
	@GetMapping("getOrder")
	public String getOrder() {
		return menuService.getOrder();
	}

}
