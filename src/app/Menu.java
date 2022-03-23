package app;

import java.util.Scanner;

class Menu {
    Scanner scanner;
    Actions actions;

    Menu() {
        scanner = new Scanner(System.in);
        actions = new Actions();
    }

    void showMenu() {
        while (true) {
            System.out.print("\nEnter action (add, remove, edit, count, info, exit): ");
            String type = scanner.next();
            switch (type) {
                case "add":
                    actions.addAction();
                    break;
                case "remove":
                    actions.removeAction();
                    break;
                case "edit":
                    actions.editeAction();
                    break;
                case "count":
                    actions.countAction();
                    break;
                case "info":
                    actions.infoAction();
                    break;
                case "exit":
                    actions.exitAction();
                    return;
                default:
                    System.out.println("Uncorrected action");
            }
        }
    }
}
