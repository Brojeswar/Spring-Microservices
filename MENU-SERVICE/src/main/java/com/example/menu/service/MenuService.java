package com.example.menu.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.example.menu.model.Menu;

@Service
public class MenuService {
	
	private String orderDetails = "No order to display";

	public List<Menu> getMenu() {
		return Arrays.asList(new Menu(1, "Pizza"), new Menu(2, "Burger"));
	}

	public String getOrder() {
		return orderDetails;
	}

	@KafkaListener(topics = "Order_Details_Topic", groupId = "Order_Details_Group")
	public void getOrder(String order) {
		orderDetails = order;
	}

}
