package moovin.springdemo.controllers.dto;

import moovin.springdemo.domain.Contact;

public class ContactResultDTO {
    private String message;
    private Contact contact;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
