package com.demo.serviceimpl;

import java.lang.module.ResolutionException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.entities.OrderDetails;
import com.demo.entities.Product;
import com.demo.entities.Store;
import com.demo.exception.OrderCancellationException;
import com.demo.exception.ResourceNotFoundException;
import com.demo.model.ProductDTO;
import com.demo.repositories.OrderRepository;
import com.demo.repositories.ProductRepository;
import com.demo.repositories.StoreRepository;
import com.demo.service.ProductService;
import com.demo.util.Converter;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private Converter converter;

	@Autowired
	private StoreRepository storeRepository;
	@Autowired
	private OrderRepository orderRepository;


	
	@Override
	public Product createProduct(Product product) {
		// TODO Auto-generated method stub
		return productRepository.save(product);
	}
	
	
	@Override
	public Product updateProduct(int id ,Product product) {
		Product p= productRepository.findById(id).get();
		if(p!=null)
		{
			p.setProductName(p.getProductName());
			p.setProductPrice(p.getProductPrice());
            p.setStock(p.getStock());
            p.setStore(p.getStore());
 
            		}
		return productRepository.save(p);
	


	}
	
	 

	@Override
	public String deleteProduct(int id) {
		Product p = productRepository.findById(id).get();
		productRepository.delete(p);
		return "Product delete successfully";
	}

	@Override
	public List<ProductDTO> getAllProduct() {
		List<Product> products = productRepository.findAll();

		List<ProductDTO> productDTOS = new ArrayList<>();

		for (Product p : products) {
			productDTOS.add(converter.convertToProductDTO(p));
		}
		return productDTOS;
	}

	@Override
	public ProductDTO getProductById(int id) throws ResourceNotFoundException {
		Optional<Product> prod = productRepository.findById(id);
		Product p;
		if (prod.isPresent()) {
			p = prod.get();
		} else {
			throw new ResourceNotFoundException("Product", "id", id);
		}
		return converter.convertToProductDTO(p);
	}

	@Override
	public String assignStoreToProduct(int storeId, int pid) {
		Store store = storeRepository.findById(storeId).get();
		Product p = productRepository.findById(pid).get();

		p.setStore(store);

		List<Product> products = new ArrayList<>();
		products.add(p);

		store.setProducts(products);

		productRepository.save(p);
		return "Store Id added successfully";
	}

	@Override
	public String orderProduct(int productId, OrderDetails orderDetails) {
		Product p = productRepository.findById(productId).get();
		double totalAmount = 0.0;
		String message;
		if (p != null) {
			totalAmount = (orderDetails.getQuantity() * p.getProductPrice());
			orderDetails.setTotalAmount(totalAmount);
			List<Product> products = new ArrayList<>();
			products.add(p);
			orderDetails.setProducts(products);
			p.setStock(p.getStock() - orderDetails.getQuantity()); // 30-2=28
			productRepository.save(p);
			orderRepository.save(orderDetails);
			message = "Your order has been placed successfully!!" + "Your total Amount is:" + totalAmount
					+ "Your order will deliver within 7days";
		} else
			message = "Product is null";

		return message;
	}
	
	  
	
	  @Override
	    public String cancelorder(int orderId)  {
	       
	        OrderDetails order = orderRepository.findById(orderId)
	            .orElseThrow(() -> new ResourceNotFoundException("Order", "id", orderId));
	        
	        LocalDate orderDate = order.getOrderDate();
	        LocalDate currentDate = LocalDate.now();
	       
	        List<Product> products = order.getProducts();
	        for (Product product : products) {
	            product.setStock(product.getStock() + order.getQuantity());
	            productRepository.save(product);
	        }

	        
	        orderRepository.delete(order);

	        return "Order canceled successfully.";
	    }

  

}




































//long daysBetween = ChronoUnit.DAYS.between(orderDate, currentDate);