package com.simplilearn.webservice.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.simplilearn.webservice.model.Product;

@RestController
@RequestMapping("/api/consumer")
public class ProductController {

	private String apiUrl = "http://localhost:8090/products";
	private String productUrl = "http://localhost:8090/product?name=";
	
	@GetMapping("/products")
	public List<Product> getAll() {
		RestTemplate template = new RestTemplate();
		ResponseEntity<List> response = template.getForEntity(apiUrl, List.class);
		return response.getBody();
	}
	
	@GetMapping("/products/{id}")
	public Product getOne(@PathVariable("id") int id) {
		RestTemplate template = new RestTemplate();
		ResponseEntity<Product> response = template.getForEntity(apiUrl+"/"+id, Product.class);
		return response.getBody();
	}
	
	@GetMapping("/product")
	public Product getOne(@RequestParam String name) {
		RestTemplate template = new RestTemplate();
		ResponseEntity<Product> response = template.getForEntity(productUrl+name, Product.class);
		return response.getBody();
	}
	
	@PostMapping("/products")
	public Product addOne(@RequestBody Product product) {
		RestTemplate template = new RestTemplate();
		ResponseEntity<Product> response = template.postForEntity(apiUrl, product, Product.class);
		return response.getBody();
	}
	
	@PutMapping("/products")
	public String updateOne(@RequestBody Product product) {
		RestTemplate template = new RestTemplate();
		template.put(apiUrl, product);
		return "Product is updated successfull.";
	}
	
	@DeleteMapping("/products/{id}")
	public String deleteOne(@PathVariable("id") int id) {
		RestTemplate template = new RestTemplate();
		template.delete(apiUrl+"/"+id);
		return "Product is deleted successfull.";
	}
}
