package app;

import java.util.ArrayList;
import java.util.List;

class PhoneBook {
    List<Contact> list;

    PhoneBook() {
        list = new ArrayList<>();
    }

    void addContact(Contact contact) {
        list.add(contact);
    }

    List<Contact> getList() {
        return list;
    }

}
