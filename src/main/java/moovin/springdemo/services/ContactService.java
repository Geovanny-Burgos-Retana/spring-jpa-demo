package moovin.springdemo.services;

import moovin.springdemo.controllers.dto.general.contact.ContactInput;
import moovin.springdemo.controllers.dto.response.contact.ContactResponse;
import moovin.springdemo.domain.Contact;

import java.util.List;
import java.util.Optional;

public interface ContactService {
    Optional<Contact> getContact(Integer id);

    List<Contact> getContacts();

    ContactResponse createContact(ContactInput contactInput);

    Optional<Contact> updateContact(Integer id, Contact contact);

    Optional<Integer> deleteContact(Integer id);
}
