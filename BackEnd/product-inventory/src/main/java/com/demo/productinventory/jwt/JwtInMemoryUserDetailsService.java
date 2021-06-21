package com.demo.productinventory.jwt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.productinventory.model.User;
import com.demo.productinventory.repository.UserRepository;

@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {

	static List<JwtUserDetails> inMemoryUserList = new ArrayList<>();

	/*
	 * @Autowired private static UserRepository userRepository;
	 */
	
	static {
		
		  inMemoryUserList.add(new JwtUserDetails(1L, "Sameer",
		  "$2a$10$3zHzb.Npv1hfZbLEU5qsdOju/tk2je6W6PnNnY.c1ujWPcZh4PL6e",
		  "ROLE_USER_2")); inMemoryUserList.add(new JwtUserDetails(2L, "AK",
		  "$2a$10$IetbreuU5KihCkDB6/r1DOJO0VyU9lSiBcrMDT.biU7FOt2oqZDPm",
		  "ROLE_USER_2"));
		 
		/*
		 * List<User> users = userRepository.findAll();
		 * 
		 * for(User user : users) { inMemoryUserList.add(new
		 * JwtUserDetails(user.getId(), user.getUsername(), user.getPassword(),
		 * "ROLE_USER_2")); }
		 */
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<JwtUserDetails> findFirst = inMemoryUserList.stream()
				.filter(user -> user.getUsername().equals(username)).findFirst();

		if (!findFirst.isPresent()) {
			throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
		}

		return findFirst.get();
	}

}
