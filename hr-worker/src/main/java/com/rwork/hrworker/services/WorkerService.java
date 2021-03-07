package com.rwork.hrworker.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rwork.hrworker.entities.Worker;
import com.rwork.hrworker.repositories.WorkerRepository;
import com.rwork.hrworker.services.exceptions.ResourceNotFoundException;

@Service
public class WorkerService {

	@Autowired
	private WorkerRepository repository;

	public List<Worker> findAll() {
		return this.repository.findAll();
	}

	public Worker findById(Long id) {
		Optional<Worker> worker = this.repository.findById(id);
		return worker.orElseThrow(() -> new ResourceNotFoundException(id));
	}

}
