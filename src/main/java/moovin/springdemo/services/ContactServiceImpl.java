package moovin.springdemo.services;

import moovin.springdemo.domain.Contact;
import moovin.springdemo.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {
    private final ContactRepository contactRepository;

    @Autowired
    public ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public Optional<Contact> getContact(Integer id) {
        return contactRepository.findById(id);
    }

    @Override
    public List<Contact> getContacts() {
        return contactRepository.findAll();
    }

    @Override
    public Contact createContact(Contact contact) {
        return contactRepository.saveAndFlush(contact);
    }

    @Override
    public Optional<Contact> updateContact(Integer id, Contact contact) {
        if (contactRepository.findById(id).isPresent()) {
            contactRepository.saveAndFlush(contact);
            return contactRepository.findById(contact.getId());
        }
        return Optional.empty();
    }


}
