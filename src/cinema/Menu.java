package cinema;

import java.util.ArrayList;
import java.util.Scanner;

class Menu {
    Scanner scanner;
    UI ui;
    Profit profit;
    ArrayList<String> menu;

    Menu(int rows, int seatsPerRows) {
        scanner = new Scanner(System.in);
        ui = new UI(rows, seatsPerRows);
        profit = new Profit(rows, seatsPerRows);
        menu = new ArrayList<>() {{
            add("Show the seats");
            add("Buy a ticket");
            add("Statistics");
        }};
    }

    void showMenu() {
        while (true) {
            System.out.print("\n");
            for (String item : menu) {
                System.out.println(menu.indexOf(item) + 1 + ". " + item);
            }
            System.out.println("0. " + "Exit");
            int type = scanner.nextInt();
            switch (type) {
                case 1:
                    ui.print();
                    break;
                case 2:
                    buyATicket();
                    break;
                case 3:
                    getStats();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Uncorrected action");
            }
        }
    }

    void buyATicket() {
        while (true) {
            System.out.print("\n");

            System.out.println("Enter a row number: ");
            int row = scanner.nextInt();
            System.out.println("Enter a seat number in that row: ");
            int seat = scanner.nextInt();

            if (row > profit.getRows() || seat > profit.getSeatsPerRows()) {
                System.out.print("\n");
                System.out.println("Wrong input!");
            } else if (!ui.isAvailableSeat(row, seat)) {
                System.out.print("\n");
                System.out.println("That ticket has already been purchased!");
            } else {
                profit.setRow(row);
                profit.setSeat(seat);
                System.out.println("Ticket price: " + "$" + profit.getTicketPrice());
                ui.bookASeat(profit.getRow(), profit.getSeat());
                profit.stats.increment(profit.getTicketPrice());
                return;
            }
        }
    }

    void getStats() {
        System.out.printf("\nNumber of purchased tickets: %d", profit.stats.getNumberOfPurchasedTickets());
        System.out.printf("\nPercentage: %.2f%s", profit.stats.getPercentage(), "%");
        System.out.printf("\nCurrent income: %s%d", "$", profit.stats.getCurrentIncome());
        System.out.printf("\nTotal income: %s%d", "$", profit.stats.getTotalIncome());
        System.out.print("\n");
    }
}
