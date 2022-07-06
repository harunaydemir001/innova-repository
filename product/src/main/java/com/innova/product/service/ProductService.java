package com.innova.product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.innova.product.model.Order;
import com.innova.product.model.Product;
import com.innova.product.repository.ProductRepository;
import com.innova.product.service.client.OrderFeignClient;

@Service
public class ProductService {
	private ProductRepository productRepository;
	private OrderFeignClient orderFeignClient;

	public ProductService(ProductRepository productRepository, OrderFeignClient orderFeignClient) {
		super();
		this.productRepository = productRepository;
		this.orderFeignClient = orderFeignClient;
	}

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	public Product saveOneProduct(Product newProduct) {
		return productRepository.save(newProduct);
	}

	public Product getOneProduct(int productId) {
		return productRepository.findById(productId).orElse(null);
	}

	public List<Product> findByOrderByPriceDesc() {
		return productRepository.findByOrderByPriceDesc();
	}

	public Product updateOneProduct(int productId, Product newProduct) {
		Optional<Product> product = productRepository.findById(productId);
		if (product.isPresent()) {
			Product foundProduct = product.get();
			foundProduct.setName(newProduct.getName());
			foundProduct.setBrand(newProduct.getBrand());
			foundProduct.setSize(newProduct.getSize());
			foundProduct.setStockNumber(newProduct.getStockNumber());
			foundProduct.setPrice(newProduct.getPrice());
			foundProduct.setColor(newProduct.getColor());
			productRepository.save(foundProduct);
			return foundProduct;
		} else {
			return null;
		}
	}

	public Order getOneOrder(int orderId) {
		return orderFeignClient.getOneOrder(orderId);
	}

	public int stockUpdate(int productStockNumber, int OrderStockNumber) {
		return productStockNumber - OrderStockNumber;
	}

	public void updateStockNumber(int productId, int orderId) {
		Order order = getOneOrder(orderId);
		Product product= getOneProduct(productId);
		product.setStockNumber(stockUpdate(product.getStockNumber(),order.getOrderAmount()));
		productRepository.save(product);
	}
	

	public void deleteOneProduct(int productId) {
		productRepository.deleteById(productId);

	}

}
