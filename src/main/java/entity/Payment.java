package entity;

import entity.enums.PaymentMethod;
import entity.enums.PaymentStatus;

public class Payment {
    private final long id;
    private final Booking booking;
    private final double amount;
    private PaymentStatus paymentStatus;
    private String transactionId;
    private final PaymentMethod paymentMethod;

    public Payment(long id, Booking booking, double amount, PaymentMethod paymentMethod) {
        this.id = id;
        this.booking = booking;
        this.amount = amount;
        this.paymentStatus = PaymentStatus.INITIATED;
        this.paymentMethod = paymentMethod;
    }

    public void success(String transactionId){
        this.transactionId = transactionId;
        paymentStatus = PaymentStatus.SUCCESS;
    }
    public void failed(){
        paymentStatus = PaymentStatus.FAILED;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }

    public long getId() {
        return id;
    }

    public Booking getBooking() {
        return booking;
    }

    public double getAmount() {
        return amount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }
}
