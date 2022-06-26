package moovin.springdemo.domain;

import com.sun.istack.NotNull;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "point")
@Cacheable(value = false)
public class Point {
    private Integer id;
    private Double latitude;
    private Double longitude;
    private String address;
    private List<Contact> contacts;

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
    @Column(name = "latitude", nullable = false)
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Basic
    @NotNull
    @Column(name = "longitude", nullable = false)
    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @Basic
    @NotNull
    @Length(min = 3, max = 512)
    @Column(name = "address", nullable = false, length = 512)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Objects.equals(id, point.id) && Objects.equals(latitude, point.latitude) && Objects.equals(longitude, point.longitude) && Objects.equals(address, point.address) && Objects.equals(getContacts(), point.getContacts());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, latitude, longitude, address);
    }

    @Override
    public String toString() {
        return "Point{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", address='" + address + '\'' +
                ", contacts=" + getContacts() +
                '}';
    }

    /**
     * De esta manera genera la relación de un punto con varios contactos crea una
     * llave foránea en la tabla de contactos que sería lo más recomendable
     *
     * @return lista de contactos del punto
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_point", nullable = false)
    @OrderBy("id asc")
    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
