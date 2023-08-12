package com.movie.service.paymentservice;

import java.util.List;
import java.util.Optional;

import com.movie.entity.Payment;

public interface PaymentService {
	public List<Payment> getAllPayment();
	public Optional<Payment> getPayementById(int id);
	public void addPayment(Payment Payment);
}
