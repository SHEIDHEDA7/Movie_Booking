package entity;

import entity.enums.BookingStatus;
import entity.enums.PaymentMethod;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Booking {
    private final long id;
    private final User user;
    private final Show show;
    private List<SubBooking> subBookings;
    private double totalAmount;
    private BookingStatus bookingStatus;
    private final LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    public PaymentMethod paymentMethod;

    public Booking(long id, User user, Show show, PaymentMethod paymentMethod) {
        this.id = id;
        this.user = user;
        this.show = show;
        this.createdAt = LocalDateTime.now();
        this.expiresAt = LocalDateTime.now().plusMinutes(15);
        this.subBookings = new ArrayList<>();
        this.totalAmount = 0.0;
        this.bookingStatus = BookingStatus.INITIATED;
        this.paymentMethod = paymentMethod;
    }

    public void confirm(){
        if(bookingStatus != BookingStatus.PAYMENT_PENDING){
            throw new IllegalArgumentException("Invalid Booking State");
        }
        bookingStatus = BookingStatus.CONFIRMED;
    }

    public void expire(){
        if(bookingStatus == BookingStatus.PAYMENT_PENDING){
            bookingStatus = BookingStatus.EXPIRED;
        }
    }

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Show getShow() {
        return show;
    }

    public List<SubBooking> getSubBookings() {
        return subBookings;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setTotalAmount(Booking booking, double amount){
        this.totalAmount += amount;
    }
}
