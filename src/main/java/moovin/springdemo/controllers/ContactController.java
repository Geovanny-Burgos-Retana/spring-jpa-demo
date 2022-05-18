package moovin.springdemo.controllers;

import moovin.springdemo.controllers.dto.ContactInputDTO;
import moovin.springdemo.controllers.dto.ContactResultDTO;
import moovin.springdemo.domain.Contact;
import moovin.springdemo.services.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/contact")
public class ContactController {
    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @GetMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContactResultDTO> getContact(@Valid @RequestBody ContactInputDTO contactInputDTO, Errors errors) {
        HttpStatus httpStatus = HttpStatus.OK;
        ContactResultDTO contactResultDTO = new ContactResultDTO();
        if (errors.hasErrors()) {
            contactResultDTO.setMessage("ERROR " + errors);
            httpStatus = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(contactResultDTO, httpStatus);
        }
        // Si pasa el if anterior significa el json enviado al endpoint tiene una estructura válida
        // todo: gbr Validación el object Optional
        Optional<Contact> contact = contactService.getContact(contactInputDTO.getId());
        if (!contact.isPresent()) {
            contactResultDTO.setMessage("No existe contacto");
            httpStatus = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(contactResultDTO, httpStatus);
        }
        contactResultDTO.setContact(contact.get());
        contactResultDTO.setMessage("OK");
        return new ResponseEntity<>(contactResultDTO, httpStatus);
    }

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContactResultDTO> createContact(@Valid @RequestBody Contact contact, Errors errors) {
        HttpStatus httpStatus = HttpStatus.OK;
        ContactResultDTO contactResultDTO = new ContactResultDTO();
        if (errors.hasErrors()) {
            contactResultDTO.setMessage("ERROR " + errors);
            httpStatus = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(contactResultDTO, httpStatus);
        }
        // Si pasa el if anterior significa el json enviado al endpoint tiene una estructura válida
        contactResultDTO.setContact(contactService.createContact(contact));
        contactResultDTO.setMessage("OK");
        return new ResponseEntity<>(contactResultDTO, httpStatus);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContactResultDTO> updateContact(@PathVariable Integer id, @Valid @RequestBody Contact contact, Errors errors) {
        HttpStatus httpStatus = HttpStatus.OK;
        ContactResultDTO contactResultDTO = new ContactResultDTO();
        if (errors.hasErrors()) {
            contactResultDTO.setMessage("ERROR " + errors);
            httpStatus = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(contactResultDTO, httpStatus);
        }
        if (!contactService.getContact(id).isPresent()) {
            contactResultDTO.setMessage("No existe contacto");
            httpStatus = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(contactResultDTO, httpStatus);
        }
        // Si pasa el if anterior significa el json enviado al endpoint tiene una estructura válida
        contactResultDTO.setContact(contactService.updateContact(contact));
        contactResultDTO.setMessage("OK");
        return new ResponseEntity<>(contactResultDTO, httpStatus);
    }
}
