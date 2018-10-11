package com.capgemini.onlineshopping.service.impl;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.capgemini.onlineshopping.entity.LineItem;
import com.capgemini.onlineshopping.entity.Order;
import com.capgemini.onlineshopping.exceptions.OrderNotFoundException;
import com.capgemini.onlineshopping.repository.OrderRepository;
import com.capgemini.onlineshopping.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepository orderRepository;

	private HashMap<Integer, Set<LineItem>> itemCart = new HashMap<>();

	@Override
	public void addLineItem(LineItem item, int customerId) {
		Set<LineItem> tempSet = itemCart.get(customerId);
		if (tempSet == null) {
			tempSet = new HashSet<>();
			tempSet.add(item);
			itemCart.put(customerId, tempSet);
		} else {
			tempSet.add(item);
			itemCart.put(customerId, tempSet);
		}
	}

	@Override
	public void removeLineItem(LineItem item, int customerId) {
		Set<LineItem> tempSet = itemCart.get(customerId);
		if (tempSet != null) {
			tempSet.remove(item);
			itemCart.put(customerId, tempSet);
		}

	}

	@Override
	public Set<LineItem> getLineItems(int customerId) {
		Set<LineItem> tempSet = itemCart.get(customerId);
		return itemCart.get(tempSet);
	}

	@Override
	public Set<Order> getOrders(int customerId) throws OrderNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order getOrder(int orderId) throws OrderNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order submitOrder(Order order) {
		/*order.setOrderDate(LocalDate.now());
		order.setItems(itemCart.get(order.getCustomerId()));*/
		return orderRepository.save(order);
	}

	@Override
	public Order cancelOrder(int orderId) throws OrderNotFoundException {
		Optional<Order> optional = orderRepository.findById(orderId);
		if (optional.isPresent()) {
			optional.get().setStatus("cancelled");
			return orderRepository.save(optional.get());
			
		}
		return null;
		
		

	}

	@Override
	public void deleteOrder(int orderId) throws OrderNotFoundException {
		orderRepository.deleteById(orderId);

	}

	

}