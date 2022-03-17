package contacts;

import java.util.Scanner;

public class UserInterface {
    private Actions actions;
    private Scanner scanner;

    public UserInterface() {
        actions = new Actions();
        scanner = new Scanner(System.in);
    }

    public void startMenu() {
        while (true) {
            System.out.print("\nEnter action (add, remove, edit, count, info, exit): ");
            String action = scanner.nextLine();
            switch (action) {
                case "add":
                    addMenu();
                    break;
                case "remove":
                    actions.remove();
                    break;
                case "edit":
                    actions.edit();
                    break;
                case "count":
                    actions.count();
                    break;
                case "info":
                    actions.info();
                    break;
                case "exit":
                    scanner.close();
                    actions.closeScanner();
                    return;
                default:
                    System.out.println("Incorrect action.");
            }
        }
    }

    private void addMenu() {
        System.out.print("Enter the type (person, organization): ");
        String type = scanner.nextLine().toLowerCase();
        switch (type) {
            case "person":
                actions.addPerson();
                break;
            case "organization":
                actions.addOrganization();
                break;
            default:
                System.out.println("Incorrect type.");
                return;
        }
        System.out.println("The record added.");
    }

}