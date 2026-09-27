package service;

import entity.Seat;
import entity.Show;

import java.time.LocalTime;

public class DynamicPricing implements PricingStrategy{
    @Override
    public double calculatePrice(Show show, Seat seat) {
        return 0.0;
    }
}
