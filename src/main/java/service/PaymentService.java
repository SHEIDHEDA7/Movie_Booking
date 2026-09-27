package service;

import entity.*;

import java.util.ArrayList;
import java.util.List;

public class PaymentService {
    private final PaymentGateway paymentGateway;
    public PaymentService(PaymentGateway paymentGateway){
        this.paymentGateway = paymentGateway;
    }

//  Create a Payment, Try Paying using the Payment Gateway, we will get Payment Result. If its success, mark Payment Success. Else Release Seats.
    public Payment processPayment(Booking booking){
        Payment payment = new Payment(Counters.paymentCounter, booking, booking.getTotalAmount(), booking.getPaymentMethod());

        String transactionId = booking.getId() + "-" + payment.getId();
        PaymentResult paymentResult = paymentGateway.pay(transactionId);

        if(paymentResult.isSuccess()){
            payment.success(transactionId);
        }else{
            payment.failed();
            List<ShowSeat> seats = new ArrayList<>();
            for(SubBooking s : booking.getSubBookings()){
                seats.add(s.getShowSeat());
                s.getShowSeat().release();
            }
        }
        return payment;
    }

    public PaymentGateway getPaymentGateway() {
        return paymentGateway;
    }
}
