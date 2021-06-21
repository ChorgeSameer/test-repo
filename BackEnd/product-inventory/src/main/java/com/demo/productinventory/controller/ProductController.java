package com.demo.productinventory.controller;

import java.net.URI;
import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.demo.productinventory.model.Product;
import com.demo.productinventory.repository.ProductRepository;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class ProductController {
	
	@Autowired
	private ProductRepository productJpaRepository;

	@GetMapping("/users/{username}/products")
	public List<Product> getAllTodos(@PathVariable String username){
		return productJpaRepository.findByUsername(username);
	}

	@GetMapping("/users/{username}/products/{id}")
	public Product getProduct(@PathVariable String username, @PathVariable long id){
		return productJpaRepository.findById(id).get();
	}

	@DeleteMapping("/users/{username}/products/{id}")
	public ResponseEntity<Void> deleteProduct(
			@PathVariable String username, @PathVariable long id){
		
		productJpaRepository.deleteById(id);
		
		return ResponseEntity.noContent().build();
		
	}
	
	@PutMapping("/users/{username}/products/{id}")
	public ResponseEntity<Product> updateTodo(
			@PathVariable String username,
			@PathVariable long id, @RequestBody Product product){
		
		
		Product udatedProduct = productJpaRepository.save(product);
		
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
	@PostMapping("/users/{username}/products")
	public ResponseEntity<Void> createTodo(
			@PathVariable String username, @RequestBody Product product){
		
		product.setUsername(username);
		Product createdProduct = productJpaRepository.save(product);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(createdProduct.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
		

}
