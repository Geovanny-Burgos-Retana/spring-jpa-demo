package moovin.springdemo.services;

import moovin.springdemo.domain.Contact;

import java.util.List;
import java.util.Optional;

public interface ContactService {
    Optional<Contact> getContact(Integer id);

    List<Contact> getContacts();

    Contact createContact(Contact contact);

    Optional<Contact> updateContact(Integer id, Contact contact);

    Optional<Integer> deleteContact(Integer id);
}
