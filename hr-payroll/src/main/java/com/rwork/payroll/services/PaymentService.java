package com.rwork.payroll.services;

import org.springframework.stereotype.Service;

import com.rwork.payroll.entities.Payment;

@Service
public class PaymentService {
	
	public Payment getPayment(Long workerId, Integer days) {
		return new Payment("Roger", 200.0, days);
	}
}
