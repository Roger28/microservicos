package com.rwork.hrworker.resources;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rwork.hrworker.entities.Worker;
import com.rwork.hrworker.services.WorkerService;

@RestController
@RequestMapping(value = "/workers")
public class WorkerResource {
	
	private static Logger LOGGER = LoggerFactory.getLogger(WorkerResource.class);
	
	@Autowired
	private Environment env;

	@Autowired
	private WorkerService service;
	
	@GetMapping
	public ResponseEntity<List<Worker>> findAllWorkers() {
		List<Worker> workers = this.service.findAll();
		return ResponseEntity.ok().body(workers);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Worker> findById(@PathVariable Long id) {
		LOGGER.info("PORT: " + env.getProperty("local.server.port"));
		Worker worker = this.service.findById(id);
		return ResponseEntity.ok().body(worker);
	}
}
