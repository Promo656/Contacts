package com.company;

import java.lang.reflect.Field;
import java.util.*;

abstract class AbstractActions {
    abstract void addAction();

}

public class Main {
    public static void main(String[] args) {
        Actions actions = new Actions();
        actions.showAction();
    }
}

class Actions extends AbstractActions {
    Scanner scanner = new Scanner(System.in);

    void addAction() {
        Contact contact = new Contact();
        contact.addAction();
    }

    public void showAction() {
        Contact contact = new Contact();
        System.out.print("Enter action (add, remove, edit, count, info, exit): ");
        String action = scanner.next();

        switch (action) {
            case "add":
                addAction();
                break;
            case "remove":
                contact.removeAction();
                break;
            case "count":
                contact.countAction();
                break;
            case "info":
                contact.infoAction();
                break;
            case "edit":
                contact.editAction();
                break;
            case "exit":
                contact.exit();
                break;
            default:
                System.out.println("Uncorrected action");
                this.showAction();
                break;
        }
    }
}

class Organizations {
    private final ArrayList<Organization> organizations = new ArrayList<>();
    private static Organizations instance;

    private Organizations() {
    }

    public void setOrganization(Organization organization) {
        this.organizations.add(organization);
    }

    public static Organizations getInstance() {
        if (instance == null) {
            return new Organizations();
        }
        return instance;
    }
}

class Organization {
    private String name;
    private String address;
    private String number;

    public Actions actions = new Actions();
    Scanner scanner = new Scanner(System.in);

    Organization(String name, String address, String number) {
        this.name = name;
        this.address = address;
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void addAction() {
        Organizations organizations = Organizations.getInstance();

        System.out.println("Enter the organization name: ");
        this.setName(scanner.nextLine());

        System.out.println("Enter the address: ");
        this.setAddress(scanner.nextLine());


        System.out.println("Enter the number:");
        this.setNumber(scanner.nextLine());

        organizations.setOrganization(new Organization(this.name, this.address, this.number));

        System.out.println("A record created!");
        this.actions.showAction();
    }
}

class Contacts {
    private final ArrayList<Contact> contacts = new ArrayList<>();
    private static Contacts instance;

    private Contacts() {
    }

    public int getCounts() {
        return this.contacts.size();
    }

    public void showContactList() {
        for (Contact element : this.contacts) {
            System.out.println(this.contacts.indexOf(element) + 1 + ". " +
                    element.getFirstName() + " " +
                    element.getLastName() + ", " +
                    element.getBirth() + ", " +
                    element.getGender() + ", " +
                    element.getNumber());
        }
    }

    public void showMemberInfo(int index) {
        Contact member = this.contacts.get(index - 1);
        System.out.println("Name: " + member.getFirstName());
        System.out.println("Surname: " + member.getLastName());
        System.out.println("Birth date: " + member.getBirth());
        System.out.println("Gender: " + member.getGender());
        System.out.println("Number: " + member.getNumber());
    }

    public void removeContact(int index) {
        this.contacts.remove(index - 1);
    }

    public void setContact(Contact contact) {
        this.contacts.add(contact);
    }

    public void editContact(int record, String field) {
        Scanner scanner = new Scanner(System.in);
        Contact contact = contacts.get(record);

        switch (field) {
            case "name":
                System.out.print("Enter a name: ");
                contact.setFirstName(scanner.nextLine());
                break;
            case "surname":
                System.out.print("Enter a surname: ");
                contact.setLastName(scanner.nextLine());
                break;
            case "birth":
                System.out.print("Enter a birth: ");
                contact.setBirth(scanner.nextLine());
                break;
            case "gender":
                System.out.print("Enter a gender: ");
                contact.setGender(scanner.nextLine());
                break;
            case "number":
                System.out.print("Enter a number: ");
                contact.setNumber(scanner.nextLine());
                break;
            default:
                System.out.println("Uncorrected action");
        }

    }

    public static Contacts getInstance() {
        if (instance == null) {
            instance = new Contacts();
        }
        return instance;
    }
}

class Contact {
    private String firstName;
    private String lastName;
    private String birth;
    private String gender;
    private String number;

    Actions actions = new Actions();
    Scanner scanner = new Scanner(System.in);

    Contact() {
    }

    public Contact(String firstName, String lastName, String birth, String gender, String number) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birth = birth;
        this.gender = gender;
        this.number = number;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirth(String birth) {
        String regexp = "([0-9]{4}[./][0-9]{2}[./][0-9]{2}|[0-9]{2}[./][0-9]{2}[./][0-9]{4})";
        if (birth.matches(regexp)) {
            this.birth = birth;
        } else {
            this.birth = "[no data]";
            System.out.println("Bad birth date!");
        }
    }

    public void setGender(String gender) {
        String regexp = "[FM]";
        if (gender.matches(regexp)) {
            this.gender = gender;
        } else {
            this.gender = "[no data]";
            System.out.println("Bad birth date!");
        }
    }

    public void setNumber(String number) {
        String regex1 = "[+]?[a-zA-Z0-9]?([\\s-]?[a-zA-Z0-9]{2,})*";
        String regex2 = "[+]?(\\([a-zA-Z0-9]+\\))([\\s-][a-zA-Z0-9]{2,})*";
        String regex3 = "[+]?[a-zA-Z0-9]{1,}[\\s-]\\([a-zA-Z0-9]{2,}\\)([\\s-][a-zA-Z0-9]{2,})*";
//        String regex4 = "[^a-zA-Z]";
        String[] list = new String[]{regex1, regex2, regex3};
        boolean arr = Arrays.stream(list).anyMatch(el -> number.matches(el));
        if (arr) {
            this.number = number;
        } else {
            this.number = "[no number]";
            System.out.println("Wrong number format!");
        }
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getBirth() {
        return this.birth;
    }

    public String getGender() {

        return this.gender;
    }

    public String getNumber() {
        return this.number;
    }

    public Field[] getDeclaredFields() {
        return this.getClass().getDeclaredFields();
    }

    public void exit() {
        System.out.println("Good buy");
    }

    public void addAction() {
        Contacts contacts = Contacts.getInstance();

        System.out.println("Enter the name of the person:");
        this.setFirstName(scanner.nextLine());

        System.out.println("Enter the surname of the person:");
        this.setLastName(scanner.nextLine());

        System.out.println("Enter the birth of the person:");
        this.setBirth(scanner.nextLine());

        System.out.println("Enter the gender of the person (M, F):");
        this.setGender(scanner.nextLine());

        System.out.println("Enter the number:");
        this.setNumber(scanner.nextLine());

        contacts.setContact(new Contact(this.firstName, this.lastName, this.birth, this.gender, this.number));

        System.out.println("A record created!");
        this.actions.showAction();
    }

    public void removeAction() {
        Contacts contacts = Contacts.getInstance();

        if (contacts.getCounts() != 0) {
            contacts.showContactList();
            System.out.print("Select a record: ");
            contacts.removeContact(scanner.nextInt());
            System.out.println("The record removed!");
        } else {
            System.out.println("No records to remove!");
        }
        this.actions.showAction();
    }

    public void countAction() {
        Contacts contacts = Contacts.getInstance();
        System.out.println("The Phone Book has " + contacts.getCounts() + " records.");
        this.actions.showAction();
    }

    public void infoAction() {
        Contacts contacts = Contacts.getInstance();
        if (contacts.getCounts() != 0) {
            contacts.showContactList();

            System.out.println("Enter index to show info: ");
            int record = scanner.nextInt();
            contacts.showMemberInfo(record);
        } else {
            System.out.println("No records to show!");
        }
        this.actions.showAction();
    }

    public void editAction() {
        Contacts contacts = Contacts.getInstance();

        if (contacts.getCounts() != 0) {
            contacts.showContactList();
            System.out.print("Select a record: ");
            int record = scanner.nextInt();

            System.out.print("Select a field (name, surname, birth, gender, number): ");
            String field = scanner.next();
            contacts.editContact(record - 1, field);
            System.out.println("The record updated!");
        } else {
            System.out.println("No records to edit!");
        }
        this.actions.showAction();
    }
}