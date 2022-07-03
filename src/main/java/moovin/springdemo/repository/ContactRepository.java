package moovin.springdemo.repository;

import moovin.springdemo.domain.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact, Integer> {
    List<Contact> findContactsByPointsId(Integer pointId);
}
