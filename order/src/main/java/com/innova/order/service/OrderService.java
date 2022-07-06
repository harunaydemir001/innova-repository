package com.innova.order.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.innova.order.model.Order;
import com.innova.order.repository.OrderRepository;
import com.innova.order.service.client.ProductFeignClient;

@Service
public class OrderService {

	private OrderRepository orderRepository;
	private ProductFeignClient productFeignClient;

	

	public OrderService(OrderRepository orderRepository, ProductFeignClient productFeignClient) {
		this.orderRepository = orderRepository;
		this.productFeignClient = productFeignClient;
	}

	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	public Order saveOneOrder(Order newOrder) {
		return orderRepository.save(newOrder);

	}

	public Order getOneOrderById(int orderId) {
		return orderRepository.findById(orderId).orElse(null);
	}

	public Order updateOneOrder(int orderId, Order newOrder) {
		Optional<Order> order = orderRepository.findById(orderId);
		if (order.isPresent()) {
			Order foundOrder = order.get();
			foundOrder.setOrderAmount(newOrder.getOrderAmount());
			foundOrder.setOrderColor(newOrder.getOrderColor());
			foundOrder.setOrderSize(newOrder.getOrderSize());
			orderRepository.save(foundOrder);
			return foundOrder;
		} else {
			return null;
		}
	}


	
	public void deleteOneOrderById(int orderId) {
		orderRepository.deleteById(orderId);

	}

}
