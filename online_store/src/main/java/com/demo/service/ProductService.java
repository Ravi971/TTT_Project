package com.demo.service;

import java.util.List;

import com.demo.entities.OrderDetails;
import com.demo.entities.Product;
import com.demo.model.ProductDTO;

public interface ProductService {
	Product createProduct(Product product);

	Product updateProduct(int id, Product product);

	String deleteProduct(int id);

	List<ProductDTO> getAllProduct();

	ProductDTO getProductById(int id);

	String assignStoreToProduct(int storeId, int pid);

	String orderProduct(int productId, OrderDetails orderDetails);
 

	String cancelorder(int id);

}
