package contacts;

import java.time.LocalDate;
import java.util.Scanner;

public class Actions {
    private PhoneBook phoneBook;
    private Scanner scanner;

    public Actions() {
        phoneBook = new PhoneBook();
        scanner = new Scanner(System.in);
    }

    public void addPerson() {
        System.out.print("Enter the name: ");
        String name = scanner.nextLine();

        System.out.print("Enter the surname: ");
        String surname = scanner.nextLine();

        System.out.print("Enter the birth date: ");
        LocalDate birthDate = setBirthDate(scanner.nextLine());

        System.out.print("Enter the gender (M, F): ");
        String gender = setGender(scanner.nextLine());

        System.out.print("Enter the number: ");
        String number = setPhoneNumber(scanner.nextLine());

        phoneBook.addContact(
                new Person(
                        name,
                        surname,
                        birthDate,
                        gender,
                        number
                ));
    }

    public void addOrganization() {
        System.out.print("Enter the organization name: ");
        String name = scanner.nextLine();

        System.out.print("Enter the address: ");
        String address = scanner.nextLine();

        System.out.print("Enter the number: ");
        String number = setPhoneNumber(scanner.nextLine());

        phoneBook.addContact(
                new Organization(
                        name,
                        address,
                        number
                ));
    }

    public void remove() {
        if (phoneBook.getSize() == 0) {
            System.out.println("No records to remove!");
            return;
        }
        showInfo();
        System.out.print("Enter index to remove: ");
        int index = isCorrectIndex(scanner.nextLine());
        if (index == -1) {
            System.out.println("Wrong index!");
        } else {
            phoneBook.removeContact(index - 1);
            System.out.println("The record removed.");
        }
    }

    public void edit() {
        if (phoneBook.getSize() == 0) {
            System.out.println("No records to edit!");
            return;
        }
        showInfo();
        System.out.print("Select a record: ");
        int index = isCorrectIndex(scanner.nextLine());
        if (index == -1) {
            System.out.println("Wrong index!");
        } else {
            Contact contact = phoneBook.getContact(index - 1);
            switch (contact.getClass().getSimpleName()) {
                case "Person":
                    editPerson(contact);
                    break;
                case "Organization":
                    editOrganization(contact);
                    break;
            }
            System.out.println("The record updated!");
        }
    }

    private void editPerson(Contact contact) {
        Person person = (Person) contact;
        System.out.print("Select a field (name, surname, birth, gender, number): ");
        String field = scanner.nextLine().toLowerCase();
        switch (field) {
            case "name":
                System.out.print("Enter name: ");
                person.setName(scanner.nextLine());
                break;
            case "surname":
                System.out.print("Enter surname: ");
                person.setSurname(scanner.nextLine());
                break;
            case "birth":
                System.out.print("Enter birth date: ");
                person.setBirthDate(setBirthDate(scanner.nextLine()));
                break;
            case "gender":
                System.out.print("Enter gender: ");
                person.setGender(setGender(scanner.nextLine()));
                break;
            case "number":
                System.out.print("Enter number: ");
                person.setPhoneNumber(setPhoneNumber(scanner.nextLine()));
                break;
            default:
                System.out.println("Incorrect field.");
        }
    }

    private void editOrganization(Contact contact) {
        Organization organization = (Organization) contact;
        System.out.print("Select a field (name, address, number): ");
        String field = scanner.nextLine().toLowerCase();
        switch (field) {
            case "name":
                System.out.print("Enter name: ");
                organization.setName(scanner.nextLine());
                break;
            case "address":
                System.out.print("Enter address: ");
                organization.setAddress(scanner.nextLine());
                break;
            case "number":
                System.out.print("Enter number: ");
                organization.setPhoneNumber(setPhoneNumber(scanner.nextLine()));
                break;
            default:
                System.out.println("Incorrect field.");
        }
    }

    public void count() {
        System.out.printf("The phone book has %s records.\n", phoneBook.getSize());
    }

    public void info() {
        if (phoneBook.getSize() == 0) {
            System.out.println("Phone book is empty.");
            return;
        }
        showInfo();
        System.out.print("Enter index to show info: ");
        int index = isCorrectIndex(scanner.nextLine());
        if (index == -1) {
            System.out.println("Wrong index!");
        } else {
            System.out.println(phoneBook.getContact(index - 1));
        }
    }

    public void closeScanner() {
        scanner.close();
    }

    private void showInfo() {
        int index = 0;
        for (Contact contact : phoneBook.getPhoneBook()) {
            System.out.printf("%d. %s\n", ++index, contact.getFullName());
        }
    }

    private LocalDate setBirthDate(String birthDate) {
        boolean validBirthDate = Validator.isValidBirthDate(birthDate);
        if (!validBirthDate) {
            System.out.println("Bad birth date!");
        }
        return validBirthDate ? LocalDate.parse(birthDate) : null;
    }

    private String setGender(String gender) {
        boolean validGender = Validator.isValidGender(gender);
        if (!validGender) {
            System.out.println("Bad gender!");
        }
        return validGender ? gender : "[no data]";
    }

    private String setPhoneNumber(String number) {
        boolean validNumber = Validator.isValidPhoneNumber(number);
        if (!validNumber) {
            System.out.println("Wrong number format!");
        }
        return validNumber ? number : "[no data]";
    }

    private int isCorrectIndex(String input) {
        int index;
        try {
            index = Integer.parseInt(input);
        } catch (Exception e) {
            index = -1;
        }
        if (index > phoneBook.getSize()) {
            index = -1;
        }
        return index;
    }
}