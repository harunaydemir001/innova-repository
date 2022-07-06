package com.innova.product.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.innova.product.model.Order;
import com.innova.product.model.Product;
import com.innova.product.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	private ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	public List<Product> getAllProducts() {
		return productService.getAllProducts();
	}

	// Ürünleri kategorilerine göre filtreleyen ve sorgulayan backend servisi
	// geliştirilecek.(Fiyata göre artan azalan listeleme)
	@GetMapping("/price")
	public List<Product> findByOrderByPriceDesc() {
		return productService.findByOrderByPriceDesc();
	}

	@GetMapping("/order/{orderId}")
	public Order getOneOrder(@PathVariable int orderId) {
		return productService.getOneOrder(orderId);
	}
	

	// Sipariş emri başarılı oluştuğunda product mikroservisi üzerinden ürün stok
	// bilgilerinin güncellendiği backend servisi.
	@PutMapping("/{productId}/{orderId}")
	public void updateStockNumber(@PathVariable int productId, @PathVariable int orderId) {
		productService.updateStockNumber(productId, orderId);
	}

	// Ürünler için oluşturma/güncelleme/silme yapan backend servisleri.
	@PostMapping
	public Product createProduct(@RequestBody Product newProduct) {
		return productService.saveOneProduct(newProduct);
	}

	@GetMapping("/{productId}")
	public Product getOneProduct(@PathVariable int productId) {
		return productService.getOneProduct(productId);
	}
	

	@PutMapping("/{productId}")
	public Product updateOneProduct(@PathVariable int productId, @RequestBody Product newProduct) {
		return productService.updateOneProduct(productId, newProduct);
	}

	@DeleteMapping("/{productId}")
	public void deleteOneProduct(@PathVariable int productId) {
		productService.deleteOneProduct(productId);
	}

}
