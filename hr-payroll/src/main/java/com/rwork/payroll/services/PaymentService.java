package com.rwork.payroll.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.rwork.payroll.entities.Payment;
import com.rwork.payroll.entities.Worker;
import com.rwork.payroll.services.exceptions.ResourceNotFoundException;

@Service
public class PaymentService {

	@Value("${hr-worker.host}")
	private String workerHost;

	@Autowired
	private RestTemplate restTemplate;

	public Payment getPayment(Long workerId, Integer days) {
		try {
			Map<String, String> params = new HashMap<>();
			params.put("id", String.valueOf(workerId));

			Worker worker = this.restTemplate.getForObject(workerHost + "/workers/{id}", Worker.class, params);
			return new Payment(worker.getName(), worker.getDailyIncome(), days);
		} catch (Exception e) {
			throw new ResourceNotFoundException(workerId);
		}
	}
}
