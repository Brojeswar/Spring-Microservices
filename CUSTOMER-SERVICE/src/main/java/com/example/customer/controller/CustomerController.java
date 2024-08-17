package com.example.customer.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.customer.model.Menu;
import com.example.customer.service.CustomerService;
import com.example.customer.service.MenuService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@RestController
@RequestMapping("/")
public class CustomerController {
	
	@Autowired
	private MenuService menuInterface;
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("getMenu")
	@CircuitBreaker(name = "customerService", fallbackMethod = "getDefaultMenu")
	public List<Menu> getMenu() {
		return menuInterface.getMenu();
	}
	
	@GetMapping("rest/api/getMenu")
	public List<Menu> getMenuUsingRestAPI() {
		return customerService.getMenuUsingRestAPI();
	}
	
	@GetMapping("provideOrder")
	public String getOrderFromCustomer(@RequestParam("order") String order) {
		return customerService.sendMsgToTopic(order);
	}
	
	public List<Menu> getDefaultMenu(Exception e) {
		System.out.println(e);
		return Stream.of(new Menu(1,"Default1"), new Menu(2,"Default2")).collect(Collectors.toList());
	}

}
