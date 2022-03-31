package cinema;

class Profit extends Values {
    Stats stats;
    private int row;
    private int seat;

    Profit(int rows, int seatsPerRows) {
        super(rows, seatsPerRows);
        this.stats = new Stats(rows, seatsPerRows);
    }


    public void setRow(int row) {
        this.row = row;
    }

    public int getRow() {
        return row;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public int getTicketPrice() {
        if (getRows() * getSeatsPerRows() < 60) {
            return 10;
        } else {
            return moreThen60();
        }
    }

    private int moreThen60() {
        if (row <= getFirstHalf()) {
            return 10;
        } else {
            return 8;
        }
    }


}
