package entity;

import entity.enums.SeatType;

public class Seat {
    private final long id;
    private final SeatType seatType;
    private final String row;
    private final String col;

    public Seat(long id, SeatType seatType, String row, String col) {
        this.id = id;
        this.seatType = seatType;
        this.row = row;
        this.col = col;
    }

    public long getId() {
        return id;
    }

    public SeatType getSeatType() {
        return seatType;
    }

    public String getRow() {
        return row;
    }

    public String getCol() {
        return col;
    }
}
