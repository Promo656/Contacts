package app;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

class Actions {
    PhoneBook phoneBook;
    Scanner scanner;

    Actions() {
        phoneBook = new PhoneBook();
        scanner = new Scanner(System.in);
    }

    void addAction() {
        System.out.print("Enter the type (person, organization): ");
        String type = scanner.nextLine();
        switch (type) {
            case "person":
                addPerson();
                break;
            case "organization":
                addOrganization();
                break;
            default:
                System.out.println("Uncorrected action");
                addAction();
        }
    }

    void addPerson() {
        System.out.print("Enter the name of the person: ");
        String name = scanner.nextLine();

        System.out.print("Enter the surname of the person: ");
        String surname = scanner.nextLine();

        System.out.print("Enter the birth of the person: ");
        LocalDate birthDate = Validator.checkBirthDate(scanner.nextLine());

        System.out.print("Enter the gender of the person (M, F): ");
        String gender = Validator.checkGender(scanner.nextLine());

        System.out.print("Enter the number: ");
        String phoneNumber = Validator.checkPhoneNumber(scanner.nextLine());

        phoneBook.addContact(new Person(name, surname, birthDate, gender, phoneNumber));
    }

    void addOrganization() {
        System.out.print("Enter the organization name: ");
        String name = scanner.nextLine();

        System.out.print("Enter the address: ");
        String address = scanner.nextLine();


        System.out.print("Enter the number: ");
        String phoneNumber = Validator.checkPhoneNumber(scanner.nextLine());

        phoneBook.addContact(new Organization(name, address, phoneNumber));

    }

    boolean searchAction() {
        if (isPhoneBookEmpty()) {
            System.out.println("No records to search!");
            return false;
        }
        System.out.print("Enter search query: ");
        String query = scanner.nextLine();
        ArrayList<Contact> searchedContacts = searchedContacts(query);
        System.out.println("Found " + searchedContacts.size() + " results:");
        showContactList(searchedContacts);
        return true;
    }

    ArrayList<Contact> searchedContacts(String query) {
        ArrayList<Contact> list = new ArrayList<>();
        String regex = ".*" + query + ".*".toLowerCase(Locale.ROOT);
        for (Contact contact : phoneBook.getList()) {
            if (contact.getClass().getSimpleName().equals("Person")) {
                String personConcat = contact.getName() + " " + ((Person) contact).getSurname();
                if (personConcat.toLowerCase(Locale.ROOT).matches(regex)) {
                    list.add(contact);
                }
            }
            if (contact.getClass().getSimpleName().equals("Organization")) {
                String organizationConcat = contact.getName();
                if (organizationConcat.toLowerCase(Locale.ROOT).matches(regex)) {
                    list.add(contact);
                }
            }
        }
        return list;
    }

    void removeAction(int index) {
        phoneBook.removeContact(index);
        System.out.println("The record removed.");
    }

    void editeAction(int index) {
        Contact contact = phoneBook.getContact(index);
        String className = contact.getClass().getSimpleName();
        switch (className) {
            case "Organization":
                editeOrganizationField(contact);
                break;
            case "Person":
                editePersonField(contact);
                break;
            default:
                System.out.println("Uncorrected action");
        }
    }

    void editeOrganizationField(Contact contact) {
        Organization organization = (Organization) contact;
        System.out.print("Select a field (name, address, number): ");
        String field = Validator.checkOrganizationField(scanner.nextLine());
        if (field == null) {
            System.out.println("Incorrect field.");
            return;
        }
        switch (field) {
            case "name":
                System.out.print("Enter a name: ");
                String name = scanner.nextLine();
                organization.setName(name);
                break;
            case "address":
                System.out.print("Enter a address: ");
                String address = scanner.nextLine();
                organization.setAddress(address);
                break;
            case "number":
                System.out.print("Enter a number: ");
                String phoneNumber = Validator.checkPhoneNumber(scanner.nextLine());
                organization.setPhoneNumber(phoneNumber);
                break;
        }
        System.out.println("Saved");
        System.out.println(organization);
    }

    void editePersonField(Contact contact) {
        Person person = (Person) contact;
        System.out.print("Select a field (name, surname, birth, gender, number): ");
        String field = Validator.checkPersonField(scanner.nextLine());
        if (field == null) {
            System.out.println("Incorrect field.");
            return;
        }
        switch (field) {
            case "name":
                System.out.print("Enter a name: ");
                String name = scanner.nextLine();
                person.setName(name);
                break;
            case "surname":
                System.out.print("Enter a surname: ");
                String surname = scanner.nextLine();
                person.setSurname(surname);
                break;
            case "birth":
                System.out.print("Enter a birth: ");
                LocalDate birthDate = Validator.checkBirthDate(scanner.nextLine());
                person.setBirthDate(birthDate);
                break;
            case "gender":
                System.out.print("Enter a gender: ");
                String gender = Validator.checkGender(scanner.nextLine());
                person.setGender(gender);
                break;
            case "number":
                System.out.print("Enter a number: ");
                String phoneNumber = Validator.checkPhoneNumber(scanner.nextLine());
                person.setPhoneNumber(phoneNumber);
                break;
        }
        System.out.println("Saved");
        System.out.println(person);
    }

    void countAction() {
        System.out.println("The Phone Book has " + phoneBook.getList().size() + " records.");
    }

    void infoAction(int value) {
        Contact contact = phoneBook.getContact(value);
        System.out.println(contact);
    }

    void exitAction() {
        System.out.println("\nGood buy");
    }

    void showContactList() {
        for (Contact contact : phoneBook.getList()) {
            System.out.println(phoneBook.getList().indexOf(contact) + 1 + ". " + contact.getName());
        }
    }

    void showContactList(ArrayList<Contact> list) {
        for (Contact contact : list) {
            System.out.println(phoneBook.getList().indexOf(contact) + 1 + ". " + contact.getName());
        }
    }

    int checkIndex(String string) {
        int index;
        try {
            index = Integer.parseInt(string);
            if (index > phoneBook.getList().size()) {
                index = -1;
            } else if (index < 1) {
                index = -1;
            }
        } catch (Exception e) {
            index = -1;
        }
        return index;
    }

    boolean isPhoneBookEmpty() {
        return phoneBook.getList().size() == 0;
    }
}
