package app;

import java.time.LocalDate;
import java.util.Scanner;

class Actions {
    PhoneBook phoneBook;
    Scanner scanner;

    Actions() {
        phoneBook = new PhoneBook();
        scanner = new Scanner(System.in);
    }

    void countAction() {
        System.out.println("The Phone Book has " + phoneBook.getList().size() + " records.");
    }

    void exitAction() {
        System.out.println("Good buy");
    }

    void addAction() {
        System.out.print("Enter the type (person, organization): ");
        String type = scanner.next();
        switch (type) {
            case "person" -> addPerson();
            case "organization" -> addOrganization();
        }

    }

    void addPerson() {
        System.out.print("Enter the name of the person: ");
        String name = scanner.nextLine();

        System.out.print("Enter the surname of the person: ");
        String surname = scanner.nextLine();

        System.out.print("Enter the birth of the person: ");
        LocalDate birthDate = LocalDate.parse(scanner.next());

        System.out.print("Enter the gender of the person (M, F): ");
        String gender = scanner.nextLine();

        System.out.print("Enter the number: ");
        String phoneNumber = scanner.nextLine();

        phoneBook.addContact(new Person(name, surname, birthDate, gender, phoneNumber));
    }

    void addOrganization() {

        System.out.println("Enter the organization name: ");
        String name = scanner.nextLine();

        System.out.println("Enter the address: ");
        String address = scanner.nextLine();


        System.out.println("Enter the number:");
        String phoneNumber = scanner.nextLine();

        phoneBook.addContact(new Organization(name, address, phoneNumber));

    }

}
