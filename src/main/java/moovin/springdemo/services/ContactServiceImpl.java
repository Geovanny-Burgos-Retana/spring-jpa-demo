package moovin.springdemo.services;

import moovin.springdemo.controllers.dto.general.contact.ContactInput;
import moovin.springdemo.controllers.dto.response.contact.ContactResponse;
import moovin.springdemo.domain.Contact;
import moovin.springdemo.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public ContactResponse createContact(ContactInput contactInput) {
        ContactResponse contactResponse = new ContactResponse();
        try {
            Contact contact = mapDtoToEntity(contactInput);
            contactResponse.setContact(contactRepository.saveAndFlush(contact));
            contactResponse.setResult(Boolean.TRUE);
            contactResponse.setStatus("SUCCESS");
        } catch (Exception ex) {
            Logger.getLogger(getClass().getName()
            ).log(Level.SEVERE, null, ex);
        }
        return contactResponse;
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

    private Contact mapDtoToEntity(ContactInput contactInput) {
        Contact contact = new Contact();
        contact.setId(contactInput.getId());
        contact.setName(contactInput.getName());
        contact.setLastName(contactInput.getLastName());
        contact.setPhone(contactInput.getPhone());
        contact.setCellPhone(contactInput.getCellPhone());
        contact.setType(contactInput.getType());
        if (contact.getPoints() == null) {
            contact.setPoints(new ArrayList<>());
        }
        contactInput.getPoints().forEach(point -> {
            contact.getPoints().add(point);
            if (point.getContacts() == null) {
                point.setContacts(new ArrayList<>());
            }
            point.getContacts().add(contact);
        });
        return contact;
    }
}
