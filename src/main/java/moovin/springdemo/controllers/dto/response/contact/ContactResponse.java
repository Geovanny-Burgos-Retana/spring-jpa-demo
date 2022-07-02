package moovin.springdemo.controllers.dto.response.contact;

import moovin.springdemo.controllers.dto.response.BaseResponse;
import moovin.springdemo.domain.Contact;

public class ContactResponse extends BaseResponse {
    private Contact contact;

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
