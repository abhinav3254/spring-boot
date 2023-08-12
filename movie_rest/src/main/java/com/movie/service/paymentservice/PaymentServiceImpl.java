package com.movie.service.paymentservice;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movie.entity.Payment;

@Service
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	PaymentRepo paymentRepo;

	@Override
	public List<Payment> getAllPayment() {
		return paymentRepo.findAll();
	}

	@Override
	public Optional<Payment> getPayementById(int id) {
		// TODO Auto-generated method stub
		return paymentRepo.findById(id);
	}

	@Override
	public void addPayment(Payment payment) {
		paymentRepo.save(payment);
	}

}
