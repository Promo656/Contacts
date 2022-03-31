package cinema;

class Stats extends Values {
    private int numberOfPurchasedTickets;
    private float percentage;
    private int currentIncome;
    private int totalIncome;

    Stats(int rows, int seatsPerRows) {
        super(rows, seatsPerRows);
    }

    public int getTotalIncome() {
        calculateTotalIncome();
        return totalIncome;
    }

    private void calculateTotalIncome() {
        if (getRows() * getSeatsPerRows() < 60) {
            totalIncome = getSeatsPerRows() * 10 * getRows();
        } else {
            totalIncome = getFirstHalf() * 10 * getSeatsPerRows() + getSecondHalf() * 8 * getSeatsPerRows();
        }
    }

    public int getCurrentIncome() {
        return currentIncome;
    }


    public void increment(int value) {
        numberOfPurchasedTickets += 1;
        currentIncome += value;
    }

    public int getNumberOfPurchasedTickets() {
        return numberOfPurchasedTickets;
    }

    public double getPercentage() {
        calculatePercentage();
        return percentage;
    }

    private void calculatePercentage() {
        percentage = (float) numberOfPurchasedTickets * 100 / (getRows() * getSeatsPerRows());

    }
}