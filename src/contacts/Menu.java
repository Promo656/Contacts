package contacts;

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
            System.out.print("\n[menu] Enter action (add, list, search, count, exit): ");
            String type = scanner.next();
            switch (type) {
                case "add":
                    actions.addAction();
                    break;
                case "list":
                    listMenu();
                    break;
                case "search":
                    searchMenu();
                    break;
                case "count":
                    actions.countAction();
                    break;
                case "exit":
                    actions.exitAction();
                    return;
                default:
                    System.out.println("Uncorrected action");
            }
        }
    }

    void listMenu() {
        if (actions.isPhoneBookEmpty()) {
            System.out.println("No records to search!");
            return;
        }
        actions.showContactList();
        System.out.print("\n[list] Enter action ([number], back): ");
        String type = Validator.checkListMenuField(scanner.next());
        Integer index = actions.checkIndex(type);
        if (type != null && index != -1) {
            if ("back".equals(type)) {
                return;
            } else if (index instanceof Integer) {
                actions.infoAction(index);
                recordMenu(index);
            }
        } else {
            System.out.println("Uncorrected action");
            listMenu();
        }
    }

    void searchMenu() {
        if (actions.searchAction()) {
            System.out.print("\n[search] Enter action ([number], back, again): ");
            String type = Validator.checkSearchMenuField(scanner.next());
            Integer index = actions.checkIndex(type);
            if (type != null && index != -1) {
                while (true) {
                    if ("back".equals(type)) {
                        return;
                    } else if ("again".equals(type)) {
                        searchMenu();
                        return;
                    } else if (index instanceof Integer) {
                        actions.infoAction(index);
                        recordMenu(index);
                        return;
                    }
                }
            } else {
                System.out.println("Uncorrected action");
                searchMenu();
            }
        }
    }

    void recordMenu(int index) {
        System.out.print("\n[record] Enter action (edit, delete, menu): ");
        String type = Validator.checkRecordMenuField(scanner.next());
        if (type != null) {
            switch (type) {
                case "menu":
                    return;
                case "delete":
                    actions.removeAction(index);
                    return;
                case "edit":
                    actions.editeAction(index);
                    recordMenu(index);
                    break;
            }
        } else {
            System.out.println("Uncorrected action");
            searchMenu();
        }
    }
}
