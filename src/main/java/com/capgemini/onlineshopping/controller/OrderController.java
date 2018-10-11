package com.capgemini.onlineshopping.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.onlineshopping.entity.LineItem;
import com.capgemini.onlineshopping.entity.Order;
import com.capgemini.onlineshopping.service.OrderService;

@RestController
public class OrderController {

	@Autowired
	OrderService orderService;

	@PostMapping("/shopping/{customerId}")
	public ResponseEntity<LineItem> addLineItem(@RequestBody LineItem item, @PathVariable int customerId) {
		orderService.addLineItem(item, customerId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/shopping/{customerId}")
	public ResponseEntity<Set<LineItem>> getLineItem(@RequestBody LineItem item,@PathVariable int customerId) {
		Set<LineItem> tempSet = orderService.getLineItems(customerId);
		return new ResponseEntity<Set<LineItem>>(tempSet, HttpStatus.OK);
	}

	@DeleteMapping("/shopping")
	public ResponseEntity<Set<LineItem>> removeLineItem(int customerId, @RequestBody LineItem item) {
		orderService.removeLineItem(item, customerId);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping("/shopping/submitorder")
	public ResponseEntity<Order> submitOrder(@RequestBody Order order) {
		return new ResponseEntity<Order>(orderService.submitOrder(order), HttpStatus.OK);

	}
	@GetMapping("/shopping/{orderId}")
	public ResponseEntity<Order> cancelOrder(@PathVariable int orderId) {
		ResponseEntity<Order> responseEntity = new ResponseEntity<Order>(orderService.cancelOrder(orderId),HttpStatus.OK);
		return responseEntity;
	}
	@DeleteMapping("/shopping/{orderId}")
	public ResponseEntity<Order> deleteOrder(@PathVariable int orderId) {
		orderService.deleteOrder(orderId);
		return new ResponseEntity<Order>(HttpStatus.OK);
}}