package com.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entities.OrderDetails;
import com.demo.entities.Product;
import com.demo.model.ProductDTO;
import com.demo.service.ProductService;
import com.demo.util.Converter;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ProductController {

	@Autowired
	private Converter converter;

	@Autowired
	private ProductService productService;


	
	@PostMapping("/createProduct")    
	public  Product createProduct(@RequestBody Product prod) {
		
		return productService.createProduct(prod);
	}
	 
	@PutMapping("/updateproduct/{id}") 
	  public Product update(@RequestHeader int  pid , @RequestBody Product product ) {
			
			return productService.updateProduct(pid, product);
		}
	 
	@GetMapping("/getallproduct")
	public List<ProductDTO>  getAllProuduct()
	{
		return productService.getAllProduct();
	}
	 

	@GetMapping("/getProductById/{id}")
	ProductDTO getProductById(@PathVariable int id) {
		return productService.getProductById(id);
	}

	@PostMapping("assign/{sid}/{pid}")
	String assignStoreToProduct(@PathVariable int sid, @PathVariable int pid) {
		return productService.assignStoreToProduct(sid, pid);
	}

	@DeleteMapping("/deleteProduct/{id}")
	String deleteProduct(@PathVariable int id) {
		return productService.deleteProduct(id);
	}

	@PostMapping("orderProduct/{pid}")
	String orderProduct(@PathVariable("pid") int productId, @RequestBody OrderDetails orderDetails) {
		return productService.orderProduct(productId, orderDetails);
	}
	
	
	@DeleteMapping("/cancelorder/{id}")
	String cancelorder(@PathVariable int id) {
		return productService.cancelorder(id);
	}
	
  
	
}
