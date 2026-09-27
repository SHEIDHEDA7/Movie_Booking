package entity;

import entity.enums.ShowSeatStatus;
import service.PricingStrategy;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ShowSeat {
    private final Show show;
    private final Seat seat;
    private ShowSeatStatus showSeatStatus;
    private double price;
    private long lockedBy;
    private LocalDateTime expiry;

    public ShowSeat(Show show, Seat seat) {
        this.show = show;
        this.seat = seat;
        this.showSeatStatus = ShowSeatStatus.AVAILABLE;
        this.lockedBy = -1;
    }

    public ShowSeat(Show show, Seat seat, PricingStrategy pricingStrategy) {
        this.show = show;
        this.seat = seat;
        this.price = pricingStrategy.calculatePrice(show, seat);
        this.showSeatStatus = ShowSeatStatus.AVAILABLE;
        this.lockedBy = -1;
    }

//    Lock: a synchronised boolean function which checks if seat is available or if is locked and expired so that it can release and lock it.
//    Confirm: Check if seat is locked, then only it can confirm. Checks if bookingId that confirmed the booking is same as the one who locked.
//    Release: Suppose seat is expired or cancelled they are released meaning lockedBy is -1 & status is set available.
    public synchronized boolean lock(long bookingId, LocalDateTime lockExpiry){
        if(showSeatStatus != ShowSeatStatus.AVAILABLE){
            if(showSeatStatus == ShowSeatStatus.LOCKED && expiry.isBefore(LocalDateTime.now())){
                release();
            }else{
                return false;
            }
        }
        showSeatStatus = ShowSeatStatus.LOCKED;
        lockedBy = bookingId;
        expiry = lockExpiry;
        return true;
    }
    public synchronized void confirm(long bookingId){
        if(showSeatStatus != ShowSeatStatus.LOCKED){
            throw new IllegalArgumentException("Seat is not Locked");
        }
        if(bookingId != lockedBy){
            throw new IllegalArgumentException("Seat is Booked by someone else");
        }
        showSeatStatus = ShowSeatStatus.BOOKED;
        expiry = null;
    }
    public synchronized void release(){
        showSeatStatus = ShowSeatStatus.AVAILABLE;
        expiry = null;
        lockedBy = -1;
    }

    public Show getShow() {
        return show;
    }

    public Seat getSeat() {
        return seat;
    }

    public ShowSeatStatus getShowSeatStatus() {
        return showSeatStatus;
    }

    public double getPrice() {
        return price;
    }

    public long getLockedBy() {
        return lockedBy;
    }

    public LocalDateTime getExpiry() {
        return expiry;
    }
}
