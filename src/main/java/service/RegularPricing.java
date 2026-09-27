package service;

import entity.Seat;
import entity.Show;

import java.time.LocalDateTime;
import java.time.LocalTime;

public class RegularPricing implements PricingStrategy{

    public static final double basePrice = 100;

    // Calculate price by getting the time and seat multiplier
    @Override
    public double calculatePrice(Show show, Seat seat) {
        double price = basePrice;
        price *= getTimeMultiplier(show);
        price *= getSeatMultiplier(seat);
        return price;
    }

    public double getTimeMultiplier(Show show){
        LocalTime endTime = show.getEnd().toLocalTime();
        if(endTime.isAfter(LocalTime.of(22, 0))){
            return 2.5;
        }
        return 1.5;
    }

    public double getSeatMultiplier(Seat seat){
        return switch (seat.getSeatType()){
            case NORMAL -> 1.0;
            case PREMIUM -> 2.0;
            case RECLINER -> 3.0;
        };
    }
}
