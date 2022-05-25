package moovin.springdemo.controllers.dto;

import moovin.springdemo.domain.Contact;

import java.util.List;

public class ContactsResultDTO {
    private String message;
    private List<Contact> contacts;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
