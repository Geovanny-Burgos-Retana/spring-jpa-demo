package moovin.springdemo.services;

import moovin.springdemo.domain.Contact;

import java.util.Optional;

public interface ContactService {
    Optional<Contact> getContact(Integer id);

    Contact createContact(Contact contact);

    Contact updateContact(Contact contact);
}
