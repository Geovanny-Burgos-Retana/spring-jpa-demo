package moovin.springdemo.controllers;

import moovin.springdemo.common.ModelErrors;
import moovin.springdemo.controllers.dto.ContactResultDTO;
import moovin.springdemo.controllers.dto.ContactsResultDTO;
import moovin.springdemo.controllers.dto.general.contact.ContactInput;
import moovin.springdemo.controllers.dto.response.ErrorInfo;
import moovin.springdemo.controllers.dto.response.contact.ContactResponse;
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

    /**
     * Crear un nuevo contacto
     *
     * @param contactInput contacto establecido con los datos para crear
     * @param errors       errores al momento de validar el @Valid @RequestBody
     * @return mensaje e información del contacto creado
     * @author Geovanny Burgos Retana
     */
    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ContactResponse> createContact(@Valid @RequestBody ContactInput contactInput,
                                                         Errors errors) {
        HttpStatus httpStatus = HttpStatus.OK;
        ContactResponse contactResponse = new ContactResponse();
        if (errors.hasErrors()) {
            contactResponse.setErrorInfo(new ErrorInfo("401", "Error en datos para crear contacto: " + new ModelErrors().getModelErrors(errors)));
            httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;
            return new ResponseEntity<>(contactResponse, httpStatus);
        }
        try {
            contactResponse = contactService.createContact(contactInput);
        } catch (Exception ex) {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(contactResponse, httpStatus);
    }

    /**
     * Leer un contacto filtrado por su ID
     *
     * @param id ID del contacto
     * @return mensaje e información del contacto obtenido
     * @author Geovanny Burgos Retana
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ContactResultDTO> getContact(@PathVariable("id") Integer id) {
        HttpStatus httpStatus = HttpStatus.OK;
        ContactResultDTO contactResultDTO = new ContactResultDTO();
        Optional<Contact> contact = contactService.getContact(id);
        // Validación de que el Optional tenga un contacto
        if (contact.isEmpty()) {
            contactResultDTO.setMessage("No existe contacto a obtener");
            httpStatus = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(contactResultDTO, httpStatus);
        }
        // Establecemos los datos en nuestro DTO de respuesta
        contactResultDTO.setContact(contact.get());
        contactResultDTO.setMessage("OK");
        return new ResponseEntity<>(contactResultDTO, httpStatus);
    }

    /**
     * Obtener todos los contactos
     *
     * @return mensaje e información de todos los contactos
     * @author Geovanny Burgos Retana
     */
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ContactsResultDTO> getContacts() {
        // todo si son mucho puede ser un dto con un atributo de lista de ids input
        HttpStatus httpStatus = HttpStatus.OK;
        ContactsResultDTO contactsResultDTO = new ContactsResultDTO();
        contactsResultDTO.setContacts(contactService.getContacts());
        contactsResultDTO.setMessage("OK");
        return new ResponseEntity<>(contactsResultDTO, httpStatus);
    }

    /**
     * Actualizar un contacto
     *
     * @param id      id del contacto
     * @param contact información nueva del contacto
     * @param errors  errores al momento de validar el @Valid @RequestBody
     * @return mensaje e información del contacto actualizado
     * @author Geovanny Burgos Retana
     */
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<ContactResultDTO> updateContact(@PathVariable Integer id,
                                                          @Valid @RequestBody Contact contact,
                                                          Errors errors) {
        HttpStatus httpStatus = HttpStatus.OK;
        ContactResultDTO contactResultDTO = new ContactResultDTO();
        if (errors.hasErrors()) {
            contactResultDTO.setMessage("ERROR " + errors);
            httpStatus = HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(contactResultDTO, httpStatus);
        }
        Optional<Contact> contactUpdate = contactService.updateContact(id, contact);
        // Validación de que el Optional tenga un contacto
        if (contactUpdate.isEmpty()) {
            contactResultDTO.setMessage("No existe contacto a actualizar");
            httpStatus = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(contactResultDTO, httpStatus);
        }
        // Establecemos los datos en nuestro DTO de respuesta
        contactResultDTO.setContact(contactUpdate.get());
        contactResultDTO.setMessage("OK");
        return new ResponseEntity<>(contactResultDTO, httpStatus);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public ResponseEntity<ContactResultDTO> deleteContact(@PathVariable Integer id) {
        HttpStatus httpStatus = HttpStatus.OK;
        ContactResultDTO contactResultDTO = new ContactResultDTO();
        Optional<Integer> contactDelete = contactService.deleteContact(id);
        if (contactDelete.isEmpty()) {
            contactResultDTO.setMessage("No existe contacto");
            httpStatus = HttpStatus.NOT_FOUND;
            return new ResponseEntity<>(contactResultDTO, httpStatus);
        }
        contactResultDTO.setMessage("OK");
        return new ResponseEntity<>(contactResultDTO, httpStatus);
    }
}
