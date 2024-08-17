package com.example.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.customer.model.Menu;

@Service
public class CustomerService {

	private static String GET_MENU_URL = "http://menu-service/getMenu";

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Autowired
	private RestTemplate restTemplate;

	public String sendMsgToTopic(String order) {
		kafkaTemplate.send("Order_Details_Topic", order);
		return "Order received!";
	}

	public List<Menu> getMenuUsingRestAPI() {
		ResponseEntity<List<Menu>> responseEntity = restTemplate.exchange(GET_MENU_URL, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Menu>>() {
				});
		return responseEntity.getBody();
	}

}
