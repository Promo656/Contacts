package app;

import java.time.LocalDateTime;

abstract class Contact {
    private String name;
    private String phoneNumber;
    private final LocalDateTime timeCreated;
    private LocalDateTime timeLastEdit;

    Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.timeCreated = LocalDateTime.now().withSecond(0).withNano(0);
        this.timeLastEdit = LocalDateTime.now().withSecond(0).withNano(0);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.timeLastEdit = LocalDateTime.now().withSecond(0).withNano(0);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        this.timeLastEdit = LocalDateTime.now().withSecond(0).withNano(0);
    }

    protected LocalDateTime getTimeCreated() {
        return timeCreated;
    }

    public LocalDateTime getTimeLastEdit() {
        return timeLastEdit;
    }
}
