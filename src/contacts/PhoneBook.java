package contacts;

import java.util.ArrayList;
import java.util.List;

public class PhoneBook {
    private List<Contact> phoneBook;

    public PhoneBook() {
        phoneBook = new ArrayList<>();
    }

    public void addContact(Contact contact) {
        phoneBook.add(contact);
    }

    public void removeContact(int recordNumber) {
        phoneBook.remove(recordNumber);
    }

    public Contact getContact(int recordNumber) {
        return phoneBook.get(recordNumber);
    }

    public int getSize() {
        return phoneBook.size();
    }

    public List<Contact> getPhoneBook() {
        return new ArrayList<>(phoneBook);
    }
}