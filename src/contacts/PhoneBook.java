package contacts;

import java.util.ArrayList;
import java.util.List;

class PhoneBook {
    private final ArrayList<Contact> list;

    PhoneBook() {
        list = new ArrayList<>();
    }

    void addContact(Contact contact) {
        list.add(contact);
    }

    void removeContact(int index) {
        list.remove(index - 1);
    }

    Contact getContact(int index) {
        return list.get(index - 1);
    }

    List<Contact> getList() {
        return list ;
    }

}
