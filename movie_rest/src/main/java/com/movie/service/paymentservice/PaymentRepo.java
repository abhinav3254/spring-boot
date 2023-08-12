package com.movie.service.paymentservice;

import org.springframework.data.jpa.repository.JpaRepository;

import com.movie.entity.Payment;

public interface PaymentRepo extends JpaRepository<Payment, Integer> {

}
