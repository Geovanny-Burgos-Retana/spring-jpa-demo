package moovin.springdemo.domain;

import com.sun.istack.NotNull;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "contact")
@Cacheable(value = false)
public class Contact {
    private Integer id;
    private String name;
    private String lastName;
    private String phone;
    private String cellPhone;
    private String type;
    private Integer reference;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "last_name", length = 100)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String firstName) {
        this.lastName = firstName;
    }

    @Basic
    @Column(name = "phone", length = 20)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "cell_phone", length = 20)
    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    @Basic
    @NotNull
    @Column(name = "type", nullable = false, length = 5)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "reference")
    // todo: Agregar el not null. Merge de JPA
    public Integer getReference() {
        return reference;
    }

    public void setReference(Integer reference) {
        this.reference = reference;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(id, contact.id) && Objects.equals(name, contact.name) && Objects.equals(lastName, contact.lastName) && Objects.equals(phone, contact.phone) && Objects.equals(cellPhone, contact.cellPhone) && Objects.equals(type, contact.type) && Objects.equals(reference, contact.reference);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, phone, cellPhone, type, reference);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phone='" + phone + '\'' +
                ", cellPhone='" + cellPhone + '\'' +
                ", type='" + type + '\'' +
                ", reference=" + reference +
                '}';
    }
}
