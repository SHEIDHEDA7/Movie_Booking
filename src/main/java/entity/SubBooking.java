package entity;

public class SubBooking {
    private final String id;
    private final ShowSeat showSeat;
    private final double price;

    public SubBooking(String id, ShowSeat showSeat, double price) {
        this.id = id;
        this.showSeat = showSeat;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public ShowSeat getShowSeat() {
        return showSeat;
    }

    public double getPrice() {
        return price;
    }
}
