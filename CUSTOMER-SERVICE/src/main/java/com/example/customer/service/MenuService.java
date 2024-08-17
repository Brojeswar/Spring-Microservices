package com.example.customer.service;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.customer.model.Menu;

@FeignClient("MENU-SERVICE")
public interface MenuService {
	
	@GetMapping("getMenu")
	public List<Menu> getMenu();

}
