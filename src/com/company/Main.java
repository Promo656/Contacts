package com.company;

import java.lang.reflect.Field;
import java.util.*;

class ContactBook {
    UUID id = UUID.randomUUID();
    ArrayList<ContactBook> list = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    void setList(ContactBook contact) {
        this.list.add(contact);
    }

    void showList() {
        if (this.list.size() != 0) {
            for (ContactBook element : this.list) {
                System.out.println(element);
            }

            System.out.println("Enter index to show info: ");
            int record = scanner.nextInt();
//            contacts.showMemberInfo(record);
        } else {
            System.out.println("No records to show!");
        }
    }

    void getList() {
        for (ContactBook element : this.list) {
            System.out.println(element);
        }
    }

    protected void exit() {
        System.out.println("Good buy");
    }

}

public class Main {
    public static void main(String[] args) {
        Actions actions = new Actions();
        actions.showAction();
    }
}

class Actions extends ContactBook {
    Scanner scanner = new Scanner(System.in);
    Contact contact = new Contact();
    Organization organization = new Organization();

    void addAction() {
        System.out.print("Enter the type (person, organization): ");
        String type = scanner.next();
        switch (type) {
            case "org" -> {
                organization.addAction();
                this.showAction();
            }
            case "person" -> {
                contact.addAction();
                this.showAction();
            }
            default -> {
                System.out.println("Uncorrected action");
                this.showAction();
            }
        }
    }

    void infoAction() {
        showList();
        this.showAction();
    }

    void exitAction() {
        exit();
    }

    void removeAction() {
        Contact contact = new Contact();
        Organization organization = new Organization();
        System.out.print("Enter the type (person, organization): ");
        String type = scanner.next();
        switch (type) {
            case "organization" -> organization.removeAction();
            case "person" -> contact.removeAction();
        }
    }

    public void showAction() {
        System.out.println(" ");
        System.out.print("Enter action (add, remove, edit, count, info, exit): ");
        String action = scanner.next();

        switch (action) {
            case "add" -> addAction();
//            case "remove" -> contact.removeAction();
//            case "count" -> contact.countAction();
            case "info" -> infoAction();
//            case "edit" -> contact.editAction();
            case "exit" -> exitAction();
            default -> {
                System.out.println("Uncorrected action");
                this.showAction();
            }
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

    public int getCounts() {
        return this.organizations.size();
    }

    public void showOrganizationList() {
        for (Organization element : this.organizations) {
            System.out.println(this.organizations.indexOf(element) + 1 + ". " + element.getName() + " " + element.getAddress() + ", " + element.getNumber());
        }
    }

    public void removeOrganization(int index) {
        this.organizations.remove(index - 1);
    }

    public static Organizations getInstance() {
        if (instance == null) {
            return new Organizations();
        }
        return instance;
    }
}

class Organization extends ContactBook {
    private UUID id;
    private String name;
    private String address;
    private String number;

    Scanner scanner = new Scanner(System.in);

    Organization() {
    }

    Organization(String name, String address, String number) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.address = address;
        this.number = number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return this.address;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return this.number;
    }

    public void addAction() {
        Organizations organizations = Organizations.getInstance();

        System.out.println("Enter the organization name: ");
        this.setName(scanner.nextLine());

        System.out.println("Enter the address: ");
        this.setAddress(scanner.nextLine());


        System.out.println("Enter the number:");
        this.setNumber(scanner.nextLine());

        setList(new Organization(this.name, this.address, this.number));

        System.out.println("A record created!");
    }

    public void removeAction() {
        Organizations organizations = Organizations.getInstance();

        if (organizations.getCounts() != 0) {
            organizations.showOrganizationList();
            System.out.print("Select a record: ");
            organizations.removeOrganization(scanner.nextInt());
            System.out.println("The record removed!");
        } else {
            System.out.println("No records to remove!");
        }
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
            System.out.println(this.contacts.indexOf(element) + 1 + ". " + element.getFirstName() + " " + element.getLastName() + ", " + element.getBirth() + ", " + element.getGender() + ", " + element.getNumber());
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

class Contact extends ContactBook {
    private UUID id;
    private String firstName;
    private String lastName;
    private String birth;
    private String gender;
    private String number;

    Scanner scanner = new Scanner(System.in);

    Contact() {
    }

    public Contact(String firstName, String lastName, String birth, String gender, String number) {
        this.id = UUID.randomUUID();
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

    public void addAction() {
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

        setList(new Contact(this.firstName, this.lastName, this.birth, this.gender, this.number));

        System.out.println("A record created!");
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
    }

    public void countAction() {
        Contacts contacts = Contacts.getInstance();
        System.out.println("The Phone Book has " + contacts.getCounts() + " records.");
    }

    public void infoAction() {
        Contacts contacts = Contacts.getInstance();
        if (this.list.size() != 0) {
            getList();

            System.out.println("Enter index to show info: ");
            int record = scanner.nextInt();
            contacts.showMemberInfo(record);
        } else {
            System.out.println("No records to show!");
        }
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
    }
}