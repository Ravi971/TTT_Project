package com.demo.model;

import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.demo.entities.Store;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
public class ProductDTO {
	@NotNull
	private int productId;
	
	@NotNull
	@Size(min = 2,max = 30,
	message = "product name should have min 2 characters and max 30 characters")
	private String productName;
	
	@NotNull
	private double productPrice;
	
	@NotNull
	private int stock;

	private Store store;
}
