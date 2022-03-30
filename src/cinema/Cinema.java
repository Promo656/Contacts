package cinema;

import java.util.ArrayList;
import java.util.Scanner;

public class Cinema {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int rows = scanner.nextInt();

        System.out.println("Enter the number of seats in each row:");
        int seatsPerRows = scanner.nextInt();

        Menu menu = new Menu(rows, seatsPerRows);
        menu.showMenu();
    }
}

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
                    showTheSeats();
                    break;
                case 2:
                    buyATicket();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Uncorrected action");
            }
        }
    }

    void showTheSeats() {
        ui.print();
    }

    void buyATicket() {
        System.out.print("\n");
        profit.setRow();
        profit.setSeat();
        profit.checkTicketPrice();
        profit.getTicketPrice();
        ui.bookSeat(profit.getRow(), profit.getSeat());
    }
}

class Profit {
    Scanner scanner = new Scanner(System.in);
    int rows;
    int seatsPerRows;
    int firstHalf;
    int secondHalf;
    int row;
    int seat;
    int ticketPrice;

    Profit(int rows, int seatsPerRows) {
        this.rows = rows;
        this.seatsPerRows = seatsPerRows;
    }

    public void setRow() {
        System.out.println("Enter a row number:");
        this.row = scanner.nextInt();
    }

    public int getRow() {
        return row;
    }

    public int getSeat() {
        return seat;
    }

    public void setSeat() {
        System.out.println("Enter a seat number in that row:");
        this.seat = scanner.nextInt();
    }

    public void getTicketPrice() {
        System.out.println("Ticket price: " + "$" + ticketPrice);
    }

    void checkTicketPrice() {
        if (rows * seatsPerRows < 60) {
            ticketPrice = 10;
        } else {
            ticketPrice = moreThen60();
        }
    }

    int moreThen60() {
        checkRows();
        if (row <= firstHalf) {
            return 10;
        } else {
            return 8;
        }
    }

    void checkRows() {
        if (rows % 2 != 0) {
            firstHalf = rows / 2;
            secondHalf = rows - firstHalf;
        } else {
            firstHalf = rows / 2;
            secondHalf = rows / 2;
        }
    }
}

class UI {
    int[][] room;

    UI(int rows, int seatsPerRows) {
        this.room = new int[rows][seatsPerRows];
    }

    void bookSeat(int row, int seat) {
        room[row - 1][seat - 1] = 1;
    }

    void print() {
        System.out.print("\n");
        System.out.println("Cinema:");
        for (int i = 0; i < room[0].length + 1; i++) {
            System.out.print(i == 0 ? "  " : i + " ");
        }
        System.out.print("\n");

        for (int i = 0; i < room.length; i++) {
            System.out.print(i + 1 + " ");
            for (int j = 0; j < room[i].length; j++) {
                System.out.print(room[i][j] == 0 ? "S " : "B ");
            }
            System.out.print("\n");
        }
    }
}