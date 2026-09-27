package service;

import entity.*;
import entity.enums.BookingStatus;
import entity.enums.PaymentMethod;
import entity.enums.ShowSeatStatus;

import java.awt.print.Book;
import java.time.LocalDateTime;
import java.util.*;

public class BookingService {
    private final PaymentService paymentService;
    public BookingService(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    /* Get the Show Seats and sort them based on Id,
       Then Create the Booking Object.
       Take expiry after 5 min.
       Iterate through the Seats snd lock them.
       If they are not locked then release all the seats and throw exception.
       Then Add the sub booking by calling addSubBookings method giving the ShowSeat.
       Set the expiry and make booking status as payment pending
    */
    public Booking createBooking(Show show, User user, List<ShowSeat> seats){
        List<ShowSeat> curSeats = new ArrayList<>();
        // Get the seat ID's, Get Show seat from show map, then add in List and sort based on ID
        // This is done because, the List Created in Main is List.of, which is immutable and cannot be sorted.
        for(ShowSeat seat : seats){
            ShowSeat showSeat = show.getShowSeats().get(seat.getSeat().getId());
            curSeats.add(showSeat);
        }

        curSeats.sort(Comparator.comparing(s -> s.getSeat().getId()));

        long bookingId = Counters.bookingCounter++;
        Booking newBooking = new Booking(bookingId, user, show, PaymentMethod.UPI);

        LocalDateTime expiry = LocalDateTime.now().plusMinutes(5);

        for(ShowSeat s : curSeats){
            boolean lock = s.lock(bookingId, expiry);

            if(!lock){
                releaseSeats(curSeats, bookingId);
                throw new IllegalArgumentException("One or more seats unavailable");
            }

            int subCnt = 1;
            String subBookingId = bookingId + "-" + subCnt++;
            addSubBooking(newBooking, new SubBooking(subBookingId, s, s.getPrice()));

        }

        newBooking.setExpiresAt(expiry);
        newBooking.setBookingStatus(BookingStatus.PAYMENT_PENDING);
        return newBooking;
    }

    // Make sure each booking has < 5 bookings then add the subBooking and change the total amount.
    public void addSubBooking(Booking booking, SubBooking subBooking){
        List<SubBooking> subBookings = booking.getSubBookings();
        if(subBookings.size() >= 4){
            throw new IllegalArgumentException("Only 4 Sub Bookings allowed per Booking");
        }else{
            subBookings.add(subBooking);
            booking.setTotalAmount(booking, subBooking.getPrice());
        }
    }

    // For each SubBooking get the ShowSeat and mark as confirm and at last make booking confirmed, then generate ticket.
    public void confirmBooking(Booking booking){
        for(SubBooking subBooking : booking.getSubBookings()){
            ShowSeat showSeat = subBooking.getShowSeat();
            showSeat.confirm(booking.getId());
        }
        booking.confirm();
        generateTicket(booking);
    }

    /* First check if booking status is confirmed if not we cannot cancel booking and throw exception
       Get the seats in booking and release them, set status to cancelled.
    * */
    public void cancelBooking(Booking booking){
        if(booking.getBookingStatus() != BookingStatus.CONFIRMED){
//            throw new IllegalArgumentException("Booking Cannot be cancelled before Confirmation");
            System.out.println("Booking Cannot be cancelled before Confirmation");
        }
        List<ShowSeat> seats = new ArrayList<>();
        for(SubBooking subBooking : booking.getSubBookings()){
            seats.add(subBooking.getShowSeat());
        }
        releaseSeats(seats, booking.getId());
        booking.setBookingStatus(BookingStatus.CANCELLED);
    }

    public void releaseSeats(List<ShowSeat> seats, long bookingId){
        for(ShowSeat s : seats){
            s.release();
        }
    }

    public PaymentService getPaymentService() {
        return paymentService;
    }

    public void generateTicket(Booking booking){
        Ticket ticket = new Ticket(booking.getId(), booking, booking.getTotalAmount(), LocalDateTime.now());
        ticket.toString();
    }

}
