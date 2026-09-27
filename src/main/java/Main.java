import entity.*;
import entity.enums.*;
import service.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        CityService cityService = new CityService();
        TheatreService theatreService = new TheatreService();
        ScreenService screenService = new ScreenService();
        ShowService showService = new ShowService();
        UserService userService = new UserService();

        PaymentService paymentService = new PaymentService(new RazorPay());
        BookingService bookingService = new BookingService(paymentService);

        // 1. CREATE MOVIE

        Movie movie = new Movie(
                1,
                "Avengers",
                Language.ENGLISH,
                Genre.ACTION,
                LocalDate.of(2020,12,1)
        );

        // 2. CREATE CITY

        City city = new City(
                1,
                "Bengaluru"
        );


        // =====================================================
        // 3. CREATE THEATRE
        // =====================================================

        Theatre theatre = new Theatre(
                1,
                "PVR",
                "Forum Mall",
                city
        );

        cityService.addTheatre(city, theatre);

        // 4. CREATE SCREEN

        Screen screen = new Screen(
                1,
                "Screen 1",
                theatre
        );

        theatreService.addScreen(theatre, screen);

        // 5. CREATE SEATS

        Seat seatA1 = new Seat(
                1,
                SeatType.NORMAL,
                "1",
                "1"
        );

        Seat seatA2 = new Seat(
                2,
                SeatType.NORMAL,
                "1",
                "2"
        );

        Seat seatA3 = new Seat(
                3,
                SeatType.RECLINER,
                "2",
                "3"
        );

        screenService.addSeat(screen, seatA1);
        screenService.addSeat(screen, seatA2);
        screenService.addSeat(screen, seatA3);

        // 6. CREATE SHOW

        Show show = new Show(
                1,
                movie,
                screen,
                LocalDateTime.of(
                        2026, 9, 17, 21, 0
                ),
                LocalDateTime.of(
                        2026, 9, 17, 23, 30
                )
        );

        screenService.addShow(screen, show);


        // 7. CREATE SHOW SEATS

        ShowSeat showSeatA1 =
                new ShowSeat(
                        show,
                        seatA1,
                        new RegularPricing()
                );

        ShowSeat showSeatA2 =
                new ShowSeat(
                        show,
                        seatA2,
                        new RegularPricing()
                );

        ShowSeat showSeatA3 =
                new ShowSeat(
                        show,
                        seatA3,
                        new RegularPricing()
                );

        showService.addShowSeat(showSeatA1);
        showService.addShowSeat(showSeatA2);
        showService.addShowSeat(showSeatA3);


        // 8. SEARCH SHOWS

        List<Show> shows =
                showService.getShows(
                        movie,
                        city,
                        LocalDate.of(
                                2026, 9, 17
                        )
                );

        System.out.println(
                "Shows found: " + shows.size()
        );


        // 9. CREATE USER

        User user = userService.createUser( 1, "Junaidh", "j@gmail.com", "987654567");

        // 10. CREATE PRICING STRATEGY


        // 11. CREATE PAYMENT GATEWAY

        PaymentGateway paymentGateway =
                new RazorPay();


        // 12. CREATE BOOKING

        // List.of makes the list immutable.
        List<ShowSeat> selectedSeats =
                List.of(
                        showSeatA1,
                        showSeatA2
                );

        Booking booking =
                bookingService.createBooking(
                        show,
                        user,
                        selectedSeats
                );


        // 13. PRINT BOOKING

        System.out.println("Booking ID: " + booking.getId());

        System.out.println("Booking Status: " + booking.getBookingStatus());

        System.out.println("Total Amount: ₹" + booking.getTotalAmount());


        // 14. PROCESS PAYMENT

        Payment payment =
                paymentService.processPayment(booking);

        boolean paymentSuccess = (payment.getPaymentStatus() == PaymentStatus.SUCCESS);

        System.out.println("Payment Success: " + paymentSuccess);


        // 15. CONFIRM BOOKING

        if (paymentSuccess) {
            bookingService.confirmBooking(booking);
            System.out.println("Booking confirmed!");
        }else{
            List<ShowSeat> showSeats = new ArrayList<>();
            for(SubBooking subBooking : booking.getSubBookings()){
                showSeats.add(subBooking.getShowSeat());
            }
            bookingService.releaseSeats(
                    showSeats, booking.getId()
            );
            System.out.println("Payment Failed!");
        }


        // 16. PRINT FINAL STATE

        System.out.println("----------------------------");

        System.out.println("Booking ID: " + booking.getId());

        System.out.println("Booking Status: " + booking.getBookingStatus());

        System.out.println("Payment Status: " + payment.getPaymentStatus());

        // 17. CHECK SEAT STATUS

        System.out.println("A1 Status: " + showSeatA1.getShowSeatStatus());
        System.out.println("A2 Status: " + showSeatA2.getShowSeatStatus());
        System.out.println("A3 Status: " + showSeatA3.getShowSeatStatus());

        // 18: Cancelling Booking
        /*bookingService.cancelBooking(booking);
        System.out.println(booking.getBookingStatus());

        System.out.println("A1 Status: " + showSeatA1.getShowSeatStatus());
        System.out.println("A2 Status: " + showSeatA2.getShowSeatStatus());
        System.out.println("A3 Status: " + showSeatA3.getShowSeatStatus());*/

    }
}