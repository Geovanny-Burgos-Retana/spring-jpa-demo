package moovin.springdemo.services;

import moovin.springdemo.domain.Contact;
import moovin.springdemo.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public Optional<Contact> createContact(Contact contact) {
        try {
            return Optional.of(contactRepository.saveAndFlush(contact));
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()
            ).log(Level.SEVERE, null, ex);
            return Optional.empty();
        }
    }

    @Override
    public Optional<Contact> updateContact(Integer id, Contact contact) {
        if (contactRepository.findById(id).isPresent()) {
            contactRepository.saveAndFlush(contact);
            return contactRepository.findById(contact.getId());
        }
        return Optional.empty();
    }

    @Override
    public Optional<Integer> deleteContact(Integer id) {
        if (contactRepository.findById(id).isPresent()) {
            contactRepository.deleteById(id);
            return Optional.of(id);
        }
        return Optional.empty();
    }


}
