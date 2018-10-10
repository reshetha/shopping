package com.capgemini.onlineshopping.service;

import java.util.Set;

import com.capgemini.onlineshopping.entity.LineItem;
import com.capgemini.onlineshopping.entity.Order;
import com.capgemini.onlineshopping.exceptions.OrderNotFoundException;

public interface OrderService {

	public void addLineItem(LineItem item, int customerId);

	public void removeLineItem(LineItem item, int customerId);

	public Set<LineItem> getLineItems(int customerId);
	
	public Set<Order> getOrders(int customerId) throws OrderNotFoundException;

	public Order getOrder(int orderId) throws OrderNotFoundException;

	public Order submitOrder(Order order);

	public void cancelOrder(int orderId) throws OrderNotFoundException;

	public void deleteOrder(Order order) throws OrderNotFoundException;
}