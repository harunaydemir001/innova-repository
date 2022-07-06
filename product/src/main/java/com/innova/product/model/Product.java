package com.innova.product.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private int id;
	@Column(name = "product_name")
	private String name;
	@Column(name = "product_size")
	private int size;
	@Column(name = "product_brand")
	private String brand;
	@Column(name = "product_stock_information")
	private int stockNumber;
	@Column(name = "product_price")
	private float price;
	@Enumerated(EnumType.STRING)
	@Column(name = "product_color")
	private Colors color;
}
