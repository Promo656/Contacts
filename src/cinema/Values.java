package cinema;

abstract class Values {
    private final int rows;
    private final int seatsPerRows;
    private final int firstHalf;
    private final int secondHalf;

    protected Values(int rows, int seatsPerRows) {
        this.rows = rows;
        this.seatsPerRows = seatsPerRows;
        if (rows % 2 != 0) {
            firstHalf = rows / 2;
            secondHalf = rows - firstHalf;
        } else {
            firstHalf = rows / 2;
            secondHalf = rows / 2;
        }
    }

    public int getFirstHalf() {
        return firstHalf;
    }

    public int getSecondHalf() {
        return secondHalf;
    }

    public int getRows() {
        return rows;
    }

    public int getSeatsPerRows() {
        return seatsPerRows;
    }
}
