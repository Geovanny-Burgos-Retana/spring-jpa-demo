package moovin.springdemo.domain;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;
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
    private ContactType type;
    private List<Point> points;

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
    @NotNull
    @Length(min = 2, max = 100)
    @Column(name = "name", length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @NotNull
    @Length(min = 2, max = 100)
    @Column(name = "last_name", length = 100)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String firstName) {
        this.lastName = firstName;
    }

    @Basic
    @NotNull
    @Pattern(regexp = "^[0-9]{8}$")
    @Column(name = "phone", length = 20)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @NotNull
    @Pattern(regexp = "^[0-9]{8}$")
    @Column(name = "cell_phone", length = 20)
    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    @Basic
    @Enumerated(EnumType.STRING)
    @NotNull
    @Column(name = "type", nullable = false, length = 5)
    public ContactType getType() {
        return type;
    }

    public void setType(ContactType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(id, contact.id) && Objects.equals(name, contact.name) && Objects.equals(lastName, contact.lastName) && Objects.equals(phone, contact.phone) && Objects.equals(cellPhone, contact.cellPhone) && Objects.equals(type, contact.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, phone, cellPhone, type);
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
                '}';
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @OrderBy("id asc")
    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }
}
