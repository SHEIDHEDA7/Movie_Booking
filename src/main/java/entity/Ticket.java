package entity;

import java.time.LocalDateTime;

public class Ticket {
    private final long id;
    private final Booking booking;
    private final double price;
    private final LocalDateTime createdAt;

    public Ticket(long id, Booking booking, double price, LocalDateTime createdAt) {
        this.id = id;
        this.booking = booking;
        this.price = price;
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Ticket:" +
                "• Ticket ID: " + id +
                "/n• Booking ID: " + booking.getId() +
                "/n• Total: " + price +
                "/n• Issued At: " + createdAt;
    }
}
