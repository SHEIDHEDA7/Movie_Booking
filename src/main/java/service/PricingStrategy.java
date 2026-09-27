package service;

import entity.Seat;
import entity.Show;

public interface PricingStrategy {
    double calculatePrice(Show show, Seat seat);
}
