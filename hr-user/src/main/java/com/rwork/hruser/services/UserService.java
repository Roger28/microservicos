package com.rwork.hruser.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rwork.hruser.entities.User;
import com.rwork.hruser.repositories.UserRepository;
import com.rwork.hruser.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {
	@Autowired
	private UserRepository repository;

	public User findById(Long id) {
		Optional<User> user = this.repository.findById(id);
		return user.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public User findByEmail(String email) {
		User user = this.repository.findByEmail(email);
		if(user == null) throw new ResourceNotFoundException(email);
		return user;
	}
}
