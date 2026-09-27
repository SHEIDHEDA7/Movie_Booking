package service;

import entity.PaymentResult;

public interface PaymentGateway {
    PaymentResult pay(String transactionId);
    PaymentResult verify(String transactionId);
    PaymentResult refund(double amount, String transactionId);
}
