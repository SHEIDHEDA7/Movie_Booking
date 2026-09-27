package service;

import entity.PaymentResult;

public class RazorPay implements PaymentGateway{
    @Override
    public PaymentResult pay(String transactionId) {
        PaymentResult p = new PaymentResult(true, transactionId, "Payment Successful");
        return p;
    }

    @Override
    public PaymentResult verify(String transactionId) {
        return null;
    }

    @Override
    public PaymentResult refund(double amount, String transactionId) {
        return null;
    }
}
