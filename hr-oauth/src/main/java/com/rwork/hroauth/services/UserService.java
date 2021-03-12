package com.rwork.hroauth.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rwork.hroauth.entities.User;
import com.rwork.hroauth.feignclients.UserFeign;
import com.rwork.hroauth.services.exceptions.ResourceNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserFeign userFeign;

	public User findById(Long id) {
		try {
			User user = this.userFeign.findById(id).getBody();
			return user;
		} catch (Exception e) {
			throw new ResourceNotFoundException(id);
		}
	}

	public User findByEmail(String email) {
		try {
			User user = this.userFeign.findByEmail(email).getBody();
			return user;
		} catch (Exception e) {
			throw new ResourceNotFoundException(email);
		}
	}
}
