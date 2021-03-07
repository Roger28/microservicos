package com.rwork.payroll.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rwork.payroll.entities.Payment;
import com.rwork.payroll.entities.Worker;
import com.rwork.payroll.feignclients.WorkerFeignClient;
import com.rwork.payroll.services.exceptions.ResourceNotFoundException;

@Service
public class PaymentService {

	@Autowired
	private WorkerFeignClient workerFeignClient;

	public Payment getPayment(Long workerId, Integer days) {
		try {
			Worker worker = this.workerFeignClient.findById(workerId).getBody();
			return new Payment(worker.getName(), worker.getDailyIncome(), days);
		} catch (Exception e) {
			throw new ResourceNotFoundException(workerId);
		}
	}
}
