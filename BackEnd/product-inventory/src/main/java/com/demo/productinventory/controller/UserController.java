package com.demo.productinventory.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.demo.productinventory.model.User;
import com.demo.productinventory.repository.UserRepository;
import com.demo.productinventory.utils.MailSender;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MailSender sendMail;
	
	@PostMapping("/add/user")
	public ResponseEntity<Void> createUser(@RequestBody User user){
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		User createdUser = userRepository.save(user);
		sendMail.sendMailWithSubject(user.getEmailid(), "Thank You For Joining Product Inventory", "Registration");
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(createdUser.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
}
