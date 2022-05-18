package moovin.springdemo.repository;

import moovin.springdemo.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
    Contact findTopByPhone(String phone);
}
